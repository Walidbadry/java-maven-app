def buildJar() {
    echo "building the application..."
    bat 'mvn package'
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        bat """
            docker build -t walid123321/java_app:1.2 .
            echo %PASS% | docker login -u %USER% --password-stdin
            docker push --quiet walid123321/java_app:1.2
        """
    }
}


def deployApp() {
    echo "deploying the application..."
}

// Return this to make methods available to Jenkins
return this
