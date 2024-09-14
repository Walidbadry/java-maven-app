def buildJar() {
    echo "building the application..."
    bat 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        bat 'docker build -t walid123321/java_app:1.0 .'  // Build Docker Image
        bat 'echo %PASS% | docker login -u %USER% --password-stdin'  // Login to Docker
        bat 'docker push walid123321/java_app:1.0'  // Push Docker Image
    }
} 

def deployApp() {
    echo 'deploying the application...'
    // Add Windows-specific deployment commands if needed
} 

return this
