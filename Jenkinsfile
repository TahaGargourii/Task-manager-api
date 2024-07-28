pipeline {
    agent {
        docker {
            image 'docker:19.03.12'
            args '--privileged'
        }
    }

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Check Docker') {
            steps {
                sh 'docker --version'
                sh 'docker run hello-world'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t taskmanager:latest .'
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                kubectl apply -f k8s/deployment.yaml
                kubectl apply -f k8s/service.yaml
                '''
            }
        }
    }
}
