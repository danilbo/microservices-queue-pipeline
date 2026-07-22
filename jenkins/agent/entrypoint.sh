#!/bin/sh
set -e

cp /keys/id_rsa.pub /home/jenkins/.ssh/authorized_keys
chown -R jenkins:jenkins /home/jenkins/.ssh
chmod 600 /home/jenkins/.ssh/authorized_keys

DOCKER_GID=$(stat -c '%g' /var/run/docker.sock)
DOCKER_GROUP=$(getent group "$DOCKER_GID" | cut -d: -f1)

if [ -z "$DOCKER_GROUP" ]; then
    groupadd -g "$DOCKER_GID" docker
    DOCKER_GROUP=docker
fi

usermod -aG "$DOCKER_GROUP" jenkins

exec /usr/sbin/sshd -D