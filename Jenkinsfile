pipeline {
    agent any

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Hardcoded credentials (replace with actual values)
                    def username = 'walid123321' // Your Docker Hub username
                    def password = 'walid@#@123321' // Your Docker Hub password or Personal Access Token

                    echo "Building the Docker image..."
                    bat "docker build -t walid123321/java_app:1.2 ."

                    // Login to Docker Hub
                    bat "echo ${password} | docker login -u ${username} --password-stdin"

                    // Push the Docker image
                    bat "docker push --quiet walid123321/java_app:1.2"
                }
            }
        }
    }
}
