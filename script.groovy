def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "Building the Docker image..."

    // Use withCredentials to securely handle Docker Hub credentials
        
        // Build the Docker image
        sh 'docker build -t walid123321/java_app:1.0 .'

        // Log in to Docker Hub
        sh "echo \$PASS | docker login -u \$USER --password-stdin"

        // Push the Docker image to Docker Hub
        sh 'docker push walid123321/java_app:1.0'
    }


def deployApp() {
    echo 'deploying the application...'
} 

return this
