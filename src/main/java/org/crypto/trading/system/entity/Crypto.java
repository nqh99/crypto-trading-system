package org.crypto.trading.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "crypto")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Crypto extends Auditable {

  @Id @NonNull @EqualsAndHashCode.Include private UUID id;

  @NonNull @EqualsAndHashCode.Include private String symbol;

  @Column private BigDecimal open;

  @Column private BigDecimal high;

  @Column private BigDecimal low;

  @Column private BigDecimal close;

  @Column private BigDecimal bid;

  @Column(precision = 15, scale = 7)
  private BigDecimal bidQty;

  @Column private BigDecimal ask;

  @Column(precision = 15, scale = 7)
  private BigDecimal askQty;
}
