package com.test.redis.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties("spring.session")
public class SessionConfig {
  @Value("${spring.session.timeout-seconds}")
  private int timeoutInSeconds;

  public int getTimeoutInSeconds() {
    return timeoutInSeconds;
  }
}
