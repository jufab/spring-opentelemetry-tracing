package fr.jufab.springtracing;

import io.opentelemetry.exporter.otlp.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SpringTracingOtelApplication {

  public static void main(String[] args) {
    System.setProperty("otel.resource.attributes", "service.name=spring-tracing-otel");
    System.setProperty("otel.exporter.otlp.span.endpoint", "localhost:55680");
    SpringApplication.run(SpringTracingOtelApplication.class, args);
  }

  /**
   * Override existing SpanExporter for Otel Exporter
   *
   * @return Tracer
   */
  @Bean
  SpanExporter otelTracerWithGrpcExporter() {
    return OtlpGrpcSpanExporter.getDefault();
  }

  /**
   * Force Sampler to 1 and Not 0.1 (default sleuth configuration)
   *
   * @return Sampler
   */
  @Bean Sampler otelSampler() {
    return Sampler.alwaysOn();
  }
}
