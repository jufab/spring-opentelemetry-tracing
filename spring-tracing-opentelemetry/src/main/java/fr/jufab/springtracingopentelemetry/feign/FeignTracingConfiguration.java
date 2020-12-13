package fr.jufab.springtracingopentelemetry.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jufab
 * @version 1.0
 */
@Configuration
public class FeignTracingConfiguration {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
