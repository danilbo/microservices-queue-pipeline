FROM docker:26-cli AS docker-cli

FROM eclipse-temurin:21-jdk-noble

RUN apt-get update && \
    apt-get install -y --no-install-recommends openssh-server git curl && \
    rm -rf /var/lib/apt/lists/*

COPY --from=docker-cli /usr/local/bin/docker /usr/local/bin/docker

RUN curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 \
    -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

RUN useradd -m -s /bin/bash jenkins && \
    mkdir -p /home/jenkins/.ssh && \
    chmod 700 /home/jenkins/.ssh

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 22
ENTRYPOINT ["/entrypoint.sh"]