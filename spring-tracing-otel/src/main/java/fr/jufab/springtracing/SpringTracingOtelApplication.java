package fr.jufab.springtracing;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SpringTracingOtelApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTracingOtelApplication.class, args);
  }

  /**
   * Service name with value otel.serviceName.
   * By default, service name is define by spring.application.name properties
   * @param serviceName
   * @return Resource
   */
/*  @Bean
  Resource otelResource(@Value("${otel.serviceName}") String serviceName) {
    return Resource.getDefault()
        .merge(Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, serviceName)));
  }*/
}
