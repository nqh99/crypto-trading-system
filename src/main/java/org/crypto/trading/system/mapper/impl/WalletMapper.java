package org.crypto.trading.system.mapper.impl;

import org.crypto.trading.system.dto.core.WalletDto;
import org.crypto.trading.system.entity.Wallet;
import org.crypto.trading.system.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper extends IMapper<Wallet, WalletDto> {
  WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);
}
