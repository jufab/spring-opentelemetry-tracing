package fr.jufab.springtracing;


import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class SpringTracingOtelApplication {

  public static void main(String[] args) {
    System.setProperty("otel.resource.attributes", "service.name=otel-tracing");
    System.setProperty("otel.exporter.otlp.span.endpoint", "localhost:55680");
    SpringApplication.run(SpringTracingOtelApplication.class, args);
  }

  /**
   * Override existing Tracer for Otel Exporter
   *
   * @return Tracer
   */
  @Bean
  Tracer otelTracerWithGrpcExporter() {
    OtlpGrpcSpanExporter spanExporter = OtlpGrpcSpanExporter.getDefault();
    BatchSpanProcessor spanProcessor =
        BatchSpanProcessor.builder(spanExporter).build();
    OpenTelemetrySdk.getGlobalTracerManagement().addSpanProcessor(spanProcessor);
    return OpenTelemetry.getGlobalTracer("spring-tracing-otel");
  }
}
