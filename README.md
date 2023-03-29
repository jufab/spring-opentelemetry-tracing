# spring-tracing

This project show how to activate tracing with SpringBoot 3.X and Opentelemetry.

## Content

[Spring Cloud Sleuth is deprecated in Spring Boot 3.X, use now Micrometer tracing.](https://spring.io/projects/spring-cloud-sleuth)

- [otel-collector](/otel-collector): docker-compose with opentelemetry-collector and, Jaeger and Zipkin for backend query
- [spring-tracing-zipkin](/spring-tracing-zipkin): Project with micrometer-tracing-bridge-brave and zipkin reporter
- [spring-tracing-otel](/spring-tracing-otel): Project with micrometer-tracing-bridge-otel and opentelemetry-exporter-zipkin
- [spring-tracing-opentelemetry](/spring-tracing-opentelemetry): Project with only OpenTelemetry

## How to

- Start docker-compose [otel-collector](/otel-collector)
- Start application [spring-tracing-zipkin](/spring-tracing-zipkin) or [spring-tracing-otel](/spring-tracing-otel) or [spring-tracing-opentelemetry](/spring-tracing-opentelemetry)
- Use [call-resource-api.sh](call-resource-api.sh) to make an api call
- Go to [Jaeger Backend](http://localhost:16686/) or [Zipkin backend](http://localhost:9412) to see result

## More info

https://spring.io/blog/2022/10/12/observability-with-spring-boot-3

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.actuator

https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/spring

