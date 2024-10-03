pipeline {
    agent any
    stages {
        stage('Test Docker Login') {
            steps {
                    echo "Building the Docker image..."
                
                    // Hardcoded credentials (not recommended for production)
                    def username = 'walid123321' // your Docker Hub username
                    def password = 'dckr_pat_bnOcmNfggc5LIUMxoem6FaJbATo' // your Docker Hub password or Personal Access Token
                
                    bat """
                        docker build -t walid123321/java_app:1.2 .
                        echo ${password} | docker login -u ${username} --password-stdin
                        docker push --quiet walid123321/java_app:1.2
                    """
                

                }
            }
        }
    }
}
