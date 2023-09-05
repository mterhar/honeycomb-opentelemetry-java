# Examples

## Example Apps

To use the examples:

- Clone or download the entire repo, as they rely on local code instead of Maven Central
- Add environment variables (or system properties)
- From the spring-agent, spring-sdk, spring-agent-only, or spring-agentless-auto directory run `../../gradlew bootRun`
- `curl` the endpoint to get a response and see data in Honeycomb.

### spring-agent

This example uses the agent along with the SDK for enriching the auto-instrumented data.

`curl localhost:5000`

### spring-sdk

This example uses the Honeycomb SDK for manual instrumentation.

`curl localhost:5001`

### spring-agent-only

This example uses ONLY the agent running alongside the app

`curl localhost:5002`

### spring-agentless-auto

This example does not use the agent but adds spring auto instrumentation

`curl localhost:5003`
