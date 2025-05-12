package org.crypto.trading.system.repository;

import java.util.UUID;
import org.crypto.trading.system.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, UUID> {}
