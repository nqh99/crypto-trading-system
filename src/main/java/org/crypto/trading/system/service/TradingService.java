package org.crypto.trading.system.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.crypto.trading.system.constant.core.BaseStatus;
import org.crypto.trading.system.constant.trade.OrderAction;
import org.crypto.trading.system.constant.trade.OrderStatus;
import org.crypto.trading.system.constant.trade.OrderType;
import org.crypto.trading.system.dto.core.CryptoDto;
import org.crypto.trading.system.dto.core.TradeOrderDto;
import org.crypto.trading.system.entity.*;
import org.crypto.trading.system.exception.BusinessException;
import org.crypto.trading.system.mapper.impl.OrderHistoryMapper;
import org.crypto.trading.system.mapper.impl.TradeOrderDtoMapper;
import org.crypto.trading.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TradingService {

  private final CryptoRepository cryptoRepository;
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;
  private final OrderRepository orderRepository;
  private final OrderHistoryRepository orderHistoryRepository;

  @Autowired
  public TradingService(
      CryptoRepository cryptoRepository,
      UserRepository userRepository,
      WalletRepository walletRepository,
      OrderRepository orderRepository,
      OrderHistoryRepository orderHistoryRepository) {
    this.cryptoRepository = cryptoRepository;
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
    this.orderRepository = orderRepository;
    this.orderHistoryRepository = orderHistoryRepository;
  }

  public List<CryptoDto> retrieveLatestAggregatedPrice(String symbol) {
    List<Crypto> cryptoList =
        cryptoRepository.findBySymbolStartingWithIgnoreCaseAndStatus(symbol, "N");

    if (cryptoList.isEmpty()) {
      throw new BusinessException(
          HttpStatus.NOT_FOUND, MessageFormat.format("Symbol not found: {0}", symbol));
    }

    return cryptoList.stream()
        .map(
            crypto -> {
              CryptoDto cryptoDto = new CryptoDto();

              cryptoDto.setSymbol(crypto.getSymbol());
              cryptoDto.setBid(crypto.getBid());
              cryptoDto.setAsk(crypto.getAsk());
              cryptoDto.setBidQty(crypto.getBidQty());
              cryptoDto.setAskQty(crypto.getAskQty());

              return cryptoDto;
            })
        .toList();
  }

  @Transactional
  public TradeOrderDto executeTrade(TradeOrderDto tradeOrderDto) {
    log.info("Executing trade order: {}", tradeOrderDto);

    User user =
        userRepository
            .findByIdAndStatus(UUID.fromString(tradeOrderDto.getUserId()), "N")
            .orElseThrow(
                () ->
                    new BusinessException(
                        HttpStatus.BAD_REQUEST, "User not found!", tradeOrderDto));

    Wallet spotWallet =
        walletRepository
            .findByUserIdAndStatusAndPriority(UUID.fromString(tradeOrderDto.getUserId()), "N", 0)
            .orElseThrow(
                () ->
                    new BusinessException(
                        HttpStatus.BAD_REQUEST,
                        MessageFormat.format(
                            "Spot wallet not found for user: {0}", tradeOrderDto.getUserId()),
                        tradeOrderDto));

    Crypto crypto =
        cryptoRepository
            .findByIdAndStatus(UUID.fromString(tradeOrderDto.getCryptoId()), "N")
            .orElseThrow(
                () ->
                    new BusinessException(
                        HttpStatus.NOT_FOUND,
                        MessageFormat.format(
                            "Crypto not found: [id: {0}, symbol: {1}]",
                            tradeOrderDto.getCryptoId(), tradeOrderDto.getSymbol())));

    Optional<WalletDetail> walletDetail =
        spotWallet.getWalletDetailList().stream()
            .filter(
                (wd) -> wd.getCrypto().getId().equals(UUID.fromString(tradeOrderDto.getCryptoId())))
            .findFirst();

    Order newOrder = TradeOrderDtoMapper.INSTANCE.toEntity(tradeOrderDto);
    newOrder.setUser(user);
    newOrder.setCrypto(crypto);

    switch (OrderAction.fromString(newOrder.getBs().trim())) {
      case BUY -> handleBuyOrder(newOrder, spotWallet, walletDetail);
      case SELL -> handleSellOrder(newOrder, spotWallet, walletDetail);
    }
    newOrder.setStatus(
        newOrder.getFilledQty().compareTo(newOrder.getQty()) == 0
            ? OrderStatus.FULL_FILLED.getStatus()
            : newOrder.getFilledQty().compareTo(BigDecimal.ZERO) > 0
                ? OrderStatus.PARTIAL_FILLED.getStatus()
                : OrderStatus.PENDING.getStatus());

    OrderHistory orderHistory = OrderHistoryMapper.INSTANCE.toDto(newOrder);

    orderRepository.save(newOrder);
    orderHistoryRepository.save(orderHistory);
    walletRepository.save(spotWallet);

    log.info(newOrder.toString());

    return TradeOrderDtoMapper.INSTANCE.toDto(newOrder);
  }

  private void handleBuyOrder(Order order, Wallet spotWallet, Optional<WalletDetail> walletDetail) {
    AtomicReference<BigDecimal> filledQty = new AtomicReference<>(BigDecimal.ZERO);
    BigDecimal price = order.getPrice();

    switch (OrderType.fromString(order.getOrderType().trim())) {
      case LIMIT -> {
        price =
            order.getPrice().compareTo(order.getCrypto().getAsk()) > 0
                ? order.getCrypto().getAsk()
                : order.getPrice();
        filledQty.set(
            order.getPrice().compareTo(order.getCrypto().getAsk()) >= 0
                ? order.getQty().compareTo(order.getCrypto().getAskQty()) <= 0
                    ? order.getQty()
                    : order.getCrypto().getAskQty()
                : BigDecimal.ZERO);
      }
      case MARKET -> {
        price = order.getCrypto().getAsk();
        filledQty.set(
            order.getQty().compareTo(order.getCrypto().getAskQty()) > 0
                ? order.getCrypto().getAskQty()
                : order.getQty());
      }
    }

    if (spotWallet.getCashBalance().compareTo(order.getQty().multiply(price)) < 0) {
      throw new BusinessException(
          HttpStatus.BAD_REQUEST,
          MessageFormat.format(
              "Insufficient cash balance in spot wallet ({0}) for trade order!",
              spotWallet.getCashBalance()));
    }

    walletDetail.ifPresentOrElse(
        wd -> wd.setAmount(wd.getAmount().add(filledQty.get())),
        () ->
            spotWallet
                .getWalletDetailList()
                .add(new WalletDetail(order.getCrypto(), filledQty.get())));

    spotWallet.setCashBalance(
        spotWallet.getCashBalance().subtract(price.multiply(filledQty.get())));

    order.setPrice(price);
    order.setFilledQty(filledQty.get());
  }

  private void handleSellOrder(
      Order order, Wallet spotWallet, Optional<WalletDetail> walletDetail) {
    AtomicReference<BigDecimal> filledQty = new AtomicReference<>(BigDecimal.ZERO);
    BigDecimal price = order.getPrice();

    switch (OrderType.fromString(order.getOrderType().trim())) {
      case LIMIT -> {
        price =
            order.getPrice().compareTo(order.getCrypto().getBid()) < 0
                ? order.getCrypto().getBid()
                : order.getPrice();
        filledQty.set(
            order.getPrice().compareTo(order.getCrypto().getBid()) <= 0
                ? order.getQty().compareTo(order.getCrypto().getBidQty()) <= 0
                    ? order.getQty()
                    : order.getCrypto().getBidQty()
                : BigDecimal.ZERO);
      }
      case MARKET -> {
        price = order.getCrypto().getBid();
        filledQty.set(
            order.getQty().compareTo(order.getCrypto().getBidQty()) > 0
                ? order.getCrypto().getBidQty()
                : order.getQty());
      }
    }

    walletDetail.ifPresentOrElse(
        (wd) -> {
          if (wd.getAmount().compareTo(order.getQty()) < 0) {
            throw new BusinessException(
                HttpStatus.BAD_REQUEST,
                MessageFormat.format(
                    "Insufficient crypto quantity ({0}) in spot wallet for trade amount ({1})!",
                    wd.getAmount(), order.getQty()));
          }

          BigDecimal currentAmount = wd.getAmount().subtract(filledQty.get());
          if (currentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            wd.setStatus(BaseStatus.DELISTED.getStatus());
            wd.setAmount(BigDecimal.ZERO);
          } else {
            wd.setAmount(currentAmount);
          }
        },
        () -> {
          throw new BusinessException(
              HttpStatus.BAD_REQUEST,
              MessageFormat.format(
                  "Does not have crypto (id: {0}) in spot wallet for sell order!",
                  order.getCrypto().getId()));
        });

    spotWallet.setCashBalance(spotWallet.getCashBalance().add(filledQty.get().multiply(price)));

    order.setPrice(price);
    order.setFilledQty(filledQty.get());
  }
}
