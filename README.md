# spring-tracing

**version Spring 2.X. See main branch** 

## Content

- [otel-collector](/otel-collector): docker-compose with opentelemetry-collector and, Jaeger and Zipkin for backend query
- [spring-tracing-zipkin](/spring-tracing-zipkin): Project with spring-cloud-sleuth-zipkin
- [spring-tracing-otel](/spring-tracing-otel): Project with spring-cloud-sleuth-otel for OpenTelemetry
- [spring-tracing-opentelemetry](/spring-tracing-opentelemetry): Project with only OpenTelemetry


## How to

- Start docker-compose [otel-collector](/otel-collector)
- Start application [spring-tracing-zipkin](/spring-tracing-zipkin) or [spring-tracing-otel](/spring-tracing-otel) or [spring-tracing-opentelemetry](/spring-tracing-opentelemetry)
- Use [call-resource-api.sh](call-resource-api.sh) to make an api call
- Go to [Jaeger Backend](http://localhost:16686/) or [Zipkin backend](http://localhost:9412) to see result
