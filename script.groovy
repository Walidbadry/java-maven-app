def buildJar() {
    echo "building the application..."
    bat 'mvn package'
}

def buildImage() {
    echo "Building the Docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker_docker', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        def buildResult = bat(script: 'docker build -t walid123321/java_app:1.2 .', returnStatus: true)
        if (buildResult != 0) {
            error "Docker build failed with status ${buildResult}"
        }

        def loginResult = bat(script: 'echo %PASS% | docker login -u %USER% --password-stdin', returnStatus: true)
        if (loginResult != 0) {
            error "Docker login failed with status ${loginResult}"
        }

        def pushResult = bat(script: 'docker push --quiet walid123321/java_app:1.2', returnStatus: true)
        if (pushResult != 0) {
            error "Docker push failed with status ${pushResult}"
        }
    }
}


def deployApp() {
    echo "deploying the application..."
}

// Return this to make methods available to Jenkins
return this
