package org.crypto.trading.system.repository;

import java.util.Optional;
import java.util.UUID;
import org.crypto.trading.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByIdAndStatus(UUID id, String status);
}
