package fr.jufab.springtracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringTracingOtelApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTracingOtelApplication.class, args);
  }
}
