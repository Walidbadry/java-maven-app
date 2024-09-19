def buildJar() {
    echo "building the application..."
    bat 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhup', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        bat """
            docker build -t walid123321/java_app:1.1 .
            echo %PASS% | docker login -u %USER% --password-stdin
            docker push --quiet walid123321/java_app:1.1
        """
    }
}


def deployApp() {
    echo "deploying the application..."
}

// Return this to make methods available to Jenkins
return this
