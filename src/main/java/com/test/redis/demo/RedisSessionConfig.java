package com.test.redis.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

@EnableRedisHttpSession
@Configuration("spring.redis-session")
//@Profile("redis-session")
public class RedisSessionConfig {
  @Value("${spring.redis-session.host}")
  private String host;

  @Value("${spring.redis-session.port}")
  private int port;

  /*
   * (non-Javadoc)
   *
   * Normally spring will try to enable Redis Keyspace events for Generic commands and Expired events.
   * However, this strategy will not work if the Redis instance has been properly secured i.e.Elastic Cache
   * Instead, a NO_OP ConfigureRedisAction bean to avoid facing errors and disable that feature
   * @see
   * https://github.com/spring-projects/spring-session/blob/master/spring-session-data-redis/src/main/java/org/springframework/session/data/redis/config/ConfigureNotifyKeyspaceEventsAction.java#L55
   */
  @Bean
  public static ConfigureRedisAction configureRedisAction() {
    return ConfigureRedisAction.NO_OP;
  }

  @Bean
  public JedisConnectionFactory connectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
    jedisConnectionFactory.setHostName(host);
    jedisConnectionFactory.setPort(port);
    return jedisConnectionFactory;
  }

  @Configuration
//  @Profile("redis-session")
  public static class CustomRedisHttpSessionConfiguration
      extends RedisHttpSessionConfiguration {

    @Autowired
    public void customize(SessionConfig sessionConfig) {
      Integer timeout = sessionConfig.getTimeoutInSeconds();
      if (timeout != null) {
        setMaxInactiveIntervalInSeconds(timeout);
      }
    }
  }
}
