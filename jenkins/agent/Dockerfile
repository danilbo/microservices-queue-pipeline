FROM eclipse-temurin:21-jdk

RUN apt-get update && \
    apt-get install -y openssh-server docker.io git curl && \
    rm -rf /var/lib/apt/lists/* && \
    mkdir /var/run/sshd

RUN useradd -m -s /bin/bash jenkins && \
    mkdir -p /home/jenkins/.ssh && \
    chmod 700 /home/jenkins/.ssh

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

EXPOSE 22
ENTRYPOINT ["/entrypoint.sh"]