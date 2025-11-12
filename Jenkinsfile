pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "gone/springboot-app:${env.BUILD_NUMBER}"
    }
    stages {
        stage('Clone') {
            steps {
                git credentialsId: 'github-cred-id', url: 'https://github.com/Necro-Coder/spring-boot-task-manager.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
                sh './gradlew test'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }
        stage('Push Docker Image') {
            steps {
                withDockerRegistry([ credentialsId: 'docker-cred-id', url: '' ]) {
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }
        stage('K8s Deploy') {
            steps {
                kubernetesDeploy(
                    configs: 'k8s/' // No hace falta kubeconfigId
                )
            }
        }
    }
}
