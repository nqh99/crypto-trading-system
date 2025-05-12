package org.crypto.trading.system.mapper;

import java.util.List;

public interface IMapper<E, D> {
  D toDto(E entity);

  E toEntity(D dto);

  List<D> toDtoList(List<E> entityList);

  List<E> toEntityList(List<D> dtoList);
}
