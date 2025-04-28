package org.crypto.trading.system.service;

import java.text.MessageFormat;
import java.util.List;
import org.crypto.trading.system.dto.core.CryptoDto;
import org.crypto.trading.system.entity.Crypto;
import org.crypto.trading.system.exception.BusinessException;
import org.crypto.trading.system.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TradingService {

  private final CryptoRepository cryptoRepository;

  @Autowired
  public TradingService(CryptoRepository cryptoRepository) {
    this.cryptoRepository = cryptoRepository;
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
}
