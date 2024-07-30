pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'task-manager-app'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git url: 'https://github.com/TahaGargourii/Task-manager-api', branch: 'develop'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests inside the Docker container (if you have any test scripts)
                    // This example assumes you have a script `run-tests.sh` for testing
                    sh 'docker run --rm $DOCKER_IMAGE:$DOCKER_TAG ./run-tests.sh'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop and remove any existing container
                    sh 'docker rm -f task-manager || true'

                    // Run the Docker container
                    sh 'docker run -d --name task-manager -p 9088:9088 $DOCKER_IMAGE:$DOCKER_TAG'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images
            script {
                sh 'docker rmi $DOCKER_IMAGE:$DOCKER_TAG || true'
            }
        }

        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}
