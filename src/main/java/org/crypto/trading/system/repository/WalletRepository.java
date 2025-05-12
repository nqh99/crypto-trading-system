package org.crypto.trading.system.repository;

import java.util.Optional;
import java.util.UUID;
import org.crypto.trading.system.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

  Optional<Wallet> findByUserIdAndStatusAndPriority(UUID userId, String status, int priority);
}
