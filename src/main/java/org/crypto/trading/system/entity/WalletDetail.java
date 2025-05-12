package org.crypto.trading.system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "wallet_detail")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class WalletDetail extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @EqualsAndHashCode.Include
  private UUID id;

  @OneToOne
  @JoinColumn(name = "crypto_id", referencedColumnName = "id", nullable = false)
  @NonNull
  private Crypto crypto;

  @Column(precision = 22, scale = 7)
  @NonNull
  private BigDecimal amount;
}
