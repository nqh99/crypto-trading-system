package org.crypto.trading.system.repository;

import java.util.UUID;
import org.crypto.trading.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, UUID> {}
