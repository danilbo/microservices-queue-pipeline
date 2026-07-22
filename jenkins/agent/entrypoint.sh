#!/bin/sh
set -e

mkdir -p /run/sshd
ssh-keygen -A

cp /keys/id_rsa.pub /home/jenkins/.ssh/authorized_keys
chown jenkins:jenkins /home/jenkins
chmod 755 /home/jenkins
chown -R jenkins:jenkins /home/jenkins/.ssh
chmod 700 /home/jenkins/.ssh
chmod 600 /home/jenkins/.ssh/authorized_keys

DOCKER_GID=$(stat -c '%g' /var/run/docker.sock)
DOCKER_GROUP=$(getent group "$DOCKER_GID" | cut -d: -f1)

if [ -z "$DOCKER_GROUP" ]; then
    groupadd -g "$DOCKER_GID" docker
    DOCKER_GROUP=docker
fi

usermod -aG "$DOCKER_GROUP" jenkins

exec /usr/sbin/sshd -D -e -o LogLevel=VERBOSE