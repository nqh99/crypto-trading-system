package org.crypto.trading.system.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.crypto.trading.system.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<Crypto, UUID> {

  List<Crypto> findAllByStatus(String status);

  List<Crypto> findBySymbolStartingWithIgnoreCaseAndStatus(String symbol, String status);

  Optional<Crypto> findByIdAndStatus(UUID id, String status);
}
