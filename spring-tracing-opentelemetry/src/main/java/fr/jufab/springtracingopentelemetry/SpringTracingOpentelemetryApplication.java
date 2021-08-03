package fr.jufab.springtracingopentelemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.instrumentation.spring.autoconfigure.EnableOpenTelemetry;
import io.opentelemetry.instrumentation.spring.autoconfigure.SamplerProperties;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableFeignClients
@EnableOpenTelemetry
public class SpringTracingOpentelemetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringTracingOpentelemetryApplication.class, args);
  }

  @Bean Resource otelResource(@Value("${otel.serviceName}") String serviceName) {
    return Resource.getDefault().merge(Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, serviceName)));
  }

  @Bean
  public OpenTelemetry openTelemetry(SamplerProperties samplerProperties,
      ContextPropagators contextPropagators,
      OtlpGrpcSpanExporter spanExporter, Resource resource) {
    return OpenTelemetrySdk.builder()
        .setTracerProvider(SdkTracerProvider.builder()
            .setResource(resource)
            .addSpanProcessor(BatchSpanProcessor.builder(spanExporter).build())
            .setSampler(Sampler.traceIdRatioBased(samplerProperties.getProbability()))
            .build())
        .setPropagators(contextPropagators)
        .buildAndRegisterGlobal();
  }
}
