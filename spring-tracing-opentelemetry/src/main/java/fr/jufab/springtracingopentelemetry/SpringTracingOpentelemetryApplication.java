package fr.jufab.springtracingopentelemetry;

import io.opentelemetry.instrumentation.spring.autoconfigure.EnableOpenTelemetryTracing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableOpenTelemetryTracing
public class SpringTracingOpentelemetryApplication {

	public static void main(String[] args) {
		System.setProperty("otel.resource.attributes", "service.name=spring-tracing-opentelemetry");
		SpringApplication.run(SpringTracingOpentelemetryApplication.class, args);
	}

}
