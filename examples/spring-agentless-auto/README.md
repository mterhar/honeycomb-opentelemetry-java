# Java No-Agent with some AutoInstrumentation

The Autoinstrumentation examples all rely on the agent.
The agent takes a long time to start up so it may not be possible to use it in some environments.

Some of the instrumentation requies the agent (like Hibernate) but others don't, like Spring and HTTP Client.
