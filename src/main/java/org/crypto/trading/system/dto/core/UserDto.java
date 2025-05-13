package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class UserDto {
  @EqualsAndHashCode.Include private UUID id;
  private String userName;
  private String password;
  private String email;
  private String phone;
  private String address;
  private String status;
}
