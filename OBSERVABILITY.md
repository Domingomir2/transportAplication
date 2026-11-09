# OpenTelemetry

The `otel-agent` Maven profile provisions the OpenTelemetry Java agent and adds it to `spring-boot:run`. The agent traces incoming HTTP requests and supported library calls; the application's Micrometer observations add spans around cost calculations and shipment repository operations. Spring Actuator exports Micrometer metrics over OTLP/HTTP.

Run an OpenTelemetry Collector that accepts OTLP/gRPC on port `4317` and OTLP/HTTP on port `4318`, then start the application in PowerShell:

```powershell
$env:OTEL_SERVICE_NAME = "transportApplication"
$env:OTEL_EXPORTER_OTLP_ENDPOINT = "http://localhost:4317"
$env:OTEL_EXPORTER_OTLP_METRICS_ENDPOINT = "http://localhost:4318/v1/metrics"
.\mvnw.cmd -Potel-agent spring-boot:run
```

By default, the Java agent samples all traces. To reduce sampling, configure both `OTEL_TRACES_SAMPLER=parentbased_traceidratio` and `OTEL_TRACES_SAMPLER_ARG` (for example, `0.1`).

For a packaged run, build with `.\mvnw.cmd -Potel-agent package`, then launch with:

```powershell
java -javaagent:target\otel\opentelemetry-javaagent.jar -jar target\transportApplication-0.0.1-SNAPSHOT.jar
```

The Collector and a trace/metrics backend are deployment requirements; this project does not start them. Only the Actuator health endpoint is exposed over HTTP.
