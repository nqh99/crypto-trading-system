package org.crypto.trading.system.dto.exchange.response;

import java.util.List;
import lombok.Data;
import org.crypto.trading.system.dto.core.CryptoDto;

@Data
public class HuobiResponseDto {
  private List<CryptoDto> data;
  private String status;
  private long ts;
}
