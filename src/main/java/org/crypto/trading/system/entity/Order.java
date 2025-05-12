package org.crypto.trading.system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "\"order\"")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Order extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @ToString.Include(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "crypto_id", nullable = false)
  @ToString.Include(name = "crypto_id")
  private Crypto crypto;

  @Column(nullable = false, length = 30)
  private String orderType;

  @Column(precision = 15, scale = 3)
  private BigDecimal price;

  @Column(precision = 22, scale = 7)
  private BigDecimal qty;

  @Column(precision = 22, scale = 7)
  private BigDecimal filledQty;

  @Column(length = 1, nullable = false)
  private String bs;
}
