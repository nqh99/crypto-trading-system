package org.crypto.trading.system.mapper.impl;

import org.crypto.trading.system.dto.core.UserDto;
import org.crypto.trading.system.entity.User;
import org.crypto.trading.system.mapper.IMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends IMapper<User, UserDto> {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Named("UserMinimalDto")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "userName", source = "userName")
  UserDto toMinimalDto(User user);
}
