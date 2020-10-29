package fr.jufab.springtracing;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SpringTracingZipkinApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTracingZipkinApplication.class, args);
  }

  //Force sample
  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

}
