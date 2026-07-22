pipeline {
    agent { label 'docker-agent' }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Deploy') {
            steps {
                dir('docker') {
                    sh 'docker-compose up -d --build'
                }
            }
        }
    }

    post {
        success {
            echo 'Stack deployed: rabbitmq, postgres, request-service, storage'
        }
        failure {
            echo 'Build/Deploy failed — read log'
        }
    }
}