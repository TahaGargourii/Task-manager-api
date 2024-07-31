pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Starting Build Stage...'
                sh './mvnw clean package -DskipTests'
                echo 'Build Stage Completed.'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Starting Deploy Stage...'
                script {
                    // Check Docker version to ensure Docker is available
                    def dockerVersion = sh(script: 'docker --version', returnStatus: true)
                    if (dockerVersion != 0) {
                        error "Docker is not installed or not available in the PATH."
                    }
                    echo 'Docker is available.'

                    // Build Docker image
                    sh 'docker build -t task-manager-app:latest .'
                    echo 'Docker image built.'

                    // Stop any running container with the same name
                    sh 'docker stop task-manager-app || true'
                    sh 'docker rm task-manager-app || true'
                    echo 'Stopped and removed any existing task-manager-app container.'

                    // Run the new container on the updated port
                    sh 'docker run -d -p 9088:9088 --name task-manager-app task-manager-app:latest'
                    echo 'Docker container started on port 9088.'
                }
            }
        }
        stage('Integration Test') {
            steps {
                echo 'Starting Integration Test Stage...'
                script {
                    // Wait for the application to start
                    sleep 10 // Adjust sleep time as necessary

                    // Test if the application is running on the correct endpoint
                    def appRunning = sh(script: 'curl -s -o /dev/null -w "%{http_code}" http://localhost:9088/api/task/all', returnStdout: true).trim()
                    if (appRunning != '200') {
                        error "Application is not responding as expected. HTTP Status: ${appRunning}"
                    }
                    echo 'Application is running and responding as expected on port 9088.'
                }
            }
        }
    }

    post {
        always {
            echo 'Starting post actions...'
            sh 'docker stop task-manager-app || true'
            sh 'docker rm task-manager-app || true'
            echo 'Stopped and removed task-manager-app container.'

            // List files in the target directory for debugging
            sh 'ls -l target'

            // Archive artifacts without looking for test reports
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            echo 'Post actions completed.'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
