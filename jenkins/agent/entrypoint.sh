#!/bin/sh
set -e

cp /keys/id_rsa.pub /home/jenkins/.ssh/authorized_keys
chown -R jenkins:jenkins /home/jenkins/.ssh
chmod 600 /home/jenkins/.ssh/authorized_keys

DOCKER_GID=$(stat -c '%g' /var/run/docker.sock)
if ! getent group docker > /dev/null; then
    groupadd -g "$DOCKER_GID" docker
fi
usermod -aG docker jenkins

exec /usr/sbin/sshd -D