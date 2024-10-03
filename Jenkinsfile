

pipeline {
    agent any
    
    environment {
        DOCKER_USERNAME = 'walid123321' // Your Docker Hub username
        DOCKER_PASSWORD = 'walid@#@123321' // Your Docker Hub password or Personal Access Token
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building the Docker image..."
                    bat "docker build -t walid123321/java_app:1.2 ."
                    bat "echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin"
                    bat "docker push --quiet walid123321/java_app:1.2"
                }
            }
        }
    }
}
