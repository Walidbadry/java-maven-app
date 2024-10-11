def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 
def buildImage() {
    echo "Building the Docker image..."

    // Use withCredentials to securely handle Docker Hub credentials
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        
        try {
            // Build the Docker image
            sh 'docker build -t walid123321/java_app:1.0 .'

            // Log in to Docker Hub
            sh "echo \$PASS | docker login -u \$USER --password-stdin"

            // Push the Docker image to Docker Hub
            def pushOutput = sh(script: 'docker push walid123321/java_app:1.0', returnStdout: true).trim()
            echo "Push output: ${pushOutput}"

        } catch (Exception e) {
            echo "An error occurred: ${e.getMessage()}"
            currentBuild.result = 'FAILURE'
            error("Stopping the build due to error: ${e.getMessage()}")
        }
    }
}



def deployApp() {
    echo 'deploying the application...'
} 

return this
