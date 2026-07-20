## Overview

This is a learning project built to practice Docker, RabbitMQ, and Jenkins CI/CD.

The system consists of two Spring Boot microservices:

- **Producer service** — accepts incoming HTTP requests and publishes messages to a RabbitMQ queue.
- **Consumer service** — listens to the queue, consumes messages, and processes them asynchronously.

Both services are containerized with Docker and deployed via a Jenkins pipeline. Jenkins is configured to watch this repository and automatically build and deploy the services on new commits.

### Tech stack
- Java / Spring Boot
- RabbitMQ (message queue)
- Docker (containerization)
- Jenkins (CI/CD, repo-triggered pipeline)