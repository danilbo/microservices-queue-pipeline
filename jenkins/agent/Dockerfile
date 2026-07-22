# syntax=docker/dockerfile:1

FROM docker:26-cli AS docker-cli

FROM eclipse-temurin:21-jdk-alpine

RUN --mount=type=cache,target=/var/cache/apk,sharing=locked \
    apk add --no-cache openssh-server git curl bash shadow

COPY --from=docker-cli /usr/local/bin/docker /usr/local/bin/docker

RUN curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 \
    -o /usr/local/bin/docker-compose && \
    chmod +x /usr/local/bin/docker-compose

RUN ln -sf "$(which java)" /usr/local/bin/java

RUN useradd -m -s /bin/bash jenkins && \
    usermod -p '*' jenkins && \
    mkdir -p /home/jenkins/.ssh && \
    chmod 700 /home/jenkins/.ssh

RUN echo "PubkeyAcceptedAlgorithms +ssh-rsa" >> /etc/ssh/sshd_config

COPY entrypoint.sh /entrypoint.sh
RUN sed -i 's/\r$//' /entrypoint.sh && chmod +x /entrypoint.sh

EXPOSE 22
ENTRYPOINT ["/entrypoint.sh"]