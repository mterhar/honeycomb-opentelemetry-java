package io.honeycomb.examples.springbootauto;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.httpclient.JavaHttpClientTelemetry;
import java.net.http.HttpClient;

import java.util.concurrent.ExecutorService;

public class JavaHttpClientConfiguration {

    // Use this HttpClient implementation for making standard http client calls.
    public HttpClient createTracedClient(OpenTelemetry openTelemetry) {
        return JavaHttpClientTelemetry.builder(openTelemetry).build().newHttpClient(createClient());
    }

    // your configuration of the Java HTTP Client goes here:
    private HttpClient createClient() {
        return HttpClient.newBuilder().build();
    }
}
