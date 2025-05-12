package org.crypto.trading.system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Wallet extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column private String name;

  @Column(precision = 20, scale = 3)
  private BigDecimal cashBalance = BigDecimal.ZERO;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "wallet_id", nullable = false)
  @ToString.Exclude
  private List<WalletDetail> walletDetailList;

  @Column(nullable = false)
  private int priority;
}
