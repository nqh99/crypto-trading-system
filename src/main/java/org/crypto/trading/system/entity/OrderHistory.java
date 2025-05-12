package org.crypto.trading.system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "order_history")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class OrderHistory extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "crypto_id", nullable = false)
  private Crypto crypto;

  @Column(nullable = false, length = 30)
  private String orderType;

  @Column(precision = 15, scale = 3)
  private BigDecimal price = BigDecimal.ZERO;

  @Column(precision = 22, scale = 7)
  private BigDecimal qty = BigDecimal.ZERO;

  @Column(precision = 22, scale = 7)
  private BigDecimal filledQty = BigDecimal.ZERO;

  @Column(length = 1, nullable = false)
  private String bs;
}
