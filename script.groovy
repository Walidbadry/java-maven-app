def buildJar() {
    echo "building the application..."
    bat 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhup', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        bat """
        docker build -t walid123321/java_app:1.0 .
        echo %PASS% | docker login -u %USER% --password-stdin
        docker push walid123321/java_app:1.0
        """
    }
}


def deployApp() {
    echo 'deploying the application...'
    // Add Windows-specific deployment commands if needed
} 

return this
