package fr.jufab.springtracing;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.TracerProvider;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
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
   * Service name with value otel.serviceName
   *
   * @param serviceName
   * @return Resource
   */
  @Bean
  Resource otelResource(@Value("${otel.serviceName}") String serviceName) {
    return Resource.create(Attributes.of(AttributeKey.stringKey("service.name"), serviceName));
  }

  /**
   * Override existing SpanExporter for Otel Exporter
   *
   * @return Tracer
   */
  @Bean
  SpanExporter otelTracerWithGrpcExporter(
      @Value("${otel.exporter.otlp.span.endpoint}") String endpoint) {
    return OtlpGrpcSpanExporter.builder()
        .readSystemProperties()
        .readEnvironmentVariables()
        .setEndpoint(endpoint)
        .build();
  }

  /**
   * TracerProvider bean for adding Resource bean and name.
   *
   * @param resource
   * @param spanExporter
   * @return TracerProvider
   */
  @Bean
  TracerProvider otelTracerProvider(Resource resource, SpanExporter spanExporter) {
    SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder().setResource(resource).build();
    sdkTracerProvider.addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build());
    return sdkTracerProvider;
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
