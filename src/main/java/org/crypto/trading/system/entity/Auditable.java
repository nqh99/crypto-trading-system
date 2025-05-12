package org.crypto.trading.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.crypto.trading.system.constant.core.BaseStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Getter
@Setter
public abstract class Auditable {

  @Column(length = 30, nullable = false)
  private String status = BaseStatus.NORMAL.getStatus();

  @Column(updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp @Column private LocalDateTime updatedAt;
}
