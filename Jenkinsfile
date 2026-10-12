pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YOUR_USERNAME/student-app.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t student-app:latest .'
                }
            }
        }
        stage('Deploy Container') {
            steps {
                script {
                    // Remove existing container if running, then start fresh container on port 8080
                    sh 'docker rm -f student-container || true'
                    sh 'docker run -d -p 8080:8080 --name student-container student-app:latest'
                }
            }
        }
    }
}