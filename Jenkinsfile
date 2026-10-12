pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/manisudhan/jenkinspipeline.git'
            }
        }
        stage('Build & Test') {
            steps {
                // 'bat' executes Windows CMD commands instead of Linux 'sh'
                bat 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t student-app:latest .'
                }
            }
        }
        stage('Deploy Container') {
            steps {
                script {
                    // Windows batch equivalent for stopping/removing old container if running
                    bat 'docker stop student-container || exit 0'
                    bat 'docker rm student-container || exit 0'
                    bat 'docker run -d -p 8080:8080 --name student-container student-app:latest'
                }
            }
        }
    }
}