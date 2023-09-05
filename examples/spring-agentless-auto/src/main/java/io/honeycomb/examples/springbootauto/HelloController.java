package io.honeycomb.examples.springbootauto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

@RestController
public class HelloController {
    private static final String importantInfo = "Important Information";

    @Autowired
    private OpenTelemetry openTelemetry;

    @GetMapping("/")
    public String index() {
        Tracer tracer = openTelemetry.getTracer("examples");
        Span span = tracer.spanBuilder("greetings").startSpan();
        span.setAttribute("custom_field", "important value");
        String intro = getImportantInfo();
        String finalMessage = String.format("%s: Greetings from Spring Boot!", intro);
        String backendResponse = callBackend();
        span.setAttribute("backend.response", backendResponse);
        span.end();
        return finalMessage;
    }

    @GetMapping("/backend")
    public String backend() {
        Tracer tracer = openTelemetry.getTracer("examples");
        Span span = tracer.spanBuilder("backend").startSpan();
        span.setAttribute("custom_field", "secret backend value");
        // Thread.sleep(200);
        span.end();
        return "Backend call complete";
    }

    public String getImportantInfo() {
        return importantInfo;
    }

    public String callBackend() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:5003/backend")).GET().build();
            JavaHttpClientConfiguration javaHttpConfig = new JavaHttpClientConfiguration();
            HttpClient httpClient = javaHttpConfig.createTracedClient(openTelemetry);
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}
