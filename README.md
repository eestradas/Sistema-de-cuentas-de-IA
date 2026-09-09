# AI Usage & Billing Platform

A Java console application that models a small-scale AI usage platform: user accounts,
direct AI queries, configurable per-user agents, and consumption-based billing.

Built to showcase clean object-oriented design in Java — an inheritance-based user model,
interface-driven abstractions, the Command pattern for handling operations, and a
reusable billing layer shared across different types of consumption.

## Features

- **User accounts** — two account tiers (free and premium) with distinct capabilities and
  usage limits, modeled through an inheritance hierarchy.
- **Direct AI queries** — send text to an AI engine and receive a response, with usage
  tracked per user.
- **Agents** — user-defined agents that process multi-part input, run each part as a
  sequential AI call, and return an aggregated, computed result.
- **Usage-based billing** — a daily free quota per user, automatic pay-per-word billing
  once the quota is exceeded, and a unified billing abstraction so different consumption
  types (calls, agent runs) are invoiced through the same mechanism without duplicated
  logic.
- **Invoicing** — on-demand invoice generation for pending usage, with items marked as
  billed so they're never charged twice.
- **Input validation** — Spanish DNI format/checksum validation, account number
  validation, and defensive handling of malformed input so the app never crashes on bad
  commands.
- **Pluggable AI engine** — all AI calls go through an interface, so the underlying engine
  (currently a lightweight stub) can be swapped for a real provider without touching any
  other part of the system.
- **Command-driven architecture** — every user action is implemented as an independent
  command object, dispatched by a central parser, rather than a monolithic
  conditional-logic block.
- **Two execution modes** — an interactive console, or batch execution from a command
  file.

Key design decisions:

- **Inheritance** models the user hierarchy (base user → free/premium), keeping
  tier-specific behavior isolated from shared logic.
- **Interfaces** decouple the AI engine and the billable-item contract from their
  concrete implementations, keeping the domain layer independent of any specific AI
  provider.
- **Command pattern** turns every operation (create user, send call, run agent, generate
  invoice) into a self-contained, testable unit, avoiding tangled conditional logic in
  the application layer.
- **Polymorphic billing** lets invoices be generated across different consumption types
  through one shared interface, with no type-checking or casting required.

Application state lives in memory for the duration of a run.

## Getting started

**Requirements:** JDK 17+, Maven

**Build:**

```bash
mvn clean package
```

**Run — interactive mode:**

```bash
java -jar target/practica-poo-ia-1.0-SNAPSHOT.jar 
```

**Run — batch mode from a command file:**

```bash
java -jar target/practica-poo-ia-1.0-SNAPSHOT.jar commands.txt
```

## Command reference

| Action | Command |
|---|---|
| Create a free account | `user add DNI name password` |
| Create a premium account | `user add DNI name password AccountNumber` |
| Send a direct AI query | `call send DNI "text to send"` |
| Create an agent | `agent add DNI AgentNumber` |
| Run an agent | `agent send DNI AgentNumber "part1#part2#part3"` |
| Generate an invoice | `bill create DNI CALL` / `AGENT` / `MIXED` |

### Example session

```
user add 12345678Z Juan clave123
user add 00000000T Ana clave456 1234567890123456789012
call send 12345678Z "hola mundo"
call send 12345678Z "uno dos tres cuatro"
agent add 00000000T 1
agent send 00000000T 1 "hola mundo#adios mundo"
call send 00000000T "uno dos tres cuatro cinco seis"
bill create 00000000T MIXED
```

## Tech stack

- **Java** — core language
- **Maven** — build and dependency management
- Design patterns: **Command**, interface-based abstraction for billing and AI access

## License

MIT
