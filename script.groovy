def buildJar() {
    echo "building the application..."
    sh 'mvn package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh sudo 'docker build -t walid123321/java_app:1.0 .'
        sh sudo "echo $PASS | docker login -u $USER --password-stdin"
        sh sudo 'docker push walid123321/java_app:1.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
