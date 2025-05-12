package org.crypto.trading.system.config.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
@ConfigurationProperties(prefix = "rest-client")
@Getter
@Setter
public class RestClientConfig {

  private int connectionTimeoutInMs;
  private int readTimeoutInMs;
  private int connectionRequestTimeoutInMs;

  @Bean
  public HttpComponentsClientHttpRequestFactory defaultHttpComponentsClientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(connectionTimeoutInMs);
    requestFactory.setReadTimeout(readTimeoutInMs);
    requestFactory.setConnectionRequestTimeout(connectionRequestTimeoutInMs);
    return requestFactory;
  }
}
