pipeline {
    agent any
    stages {
        stage('SCM Checkout') {
            steps {
                script {
                    git 'https://github.com/shovna01PS/banking-app-spring-boot-rest-api.git'
                }
            }
        }
        stage('Compile-Package') {
            steps {
                script {
                    bat 'mvn package'
                }
            }
        }
    }
}
