
def incremint_ver(){
    echo 'increment app version....'
    sh 'mvn build-helper:parse-version versions:set \
     -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.incrementalVersion+1}\
      versions:commit'
   def macher= readFile('pom.xml') =~ '<version>(.+)</version>'
   def version =macher[0][1]     
   env.IMAGE_NAME = "$version - $BUILD_NUMPER"
}
def buildJar() {
    echo "building the application..."
    sh 'mvn clean package'
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t walid123321/demo-app:$IMAGE_NAME ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push walid123321/demo-app:$IMAGE_NAME"
    }
} 

def buildImageWithECR() {
    echo "building the docker image..."
    
    withCredentials([usernamePassword(credentialsId: 'aws-ecr-credentials', passwordVariable: 'AWS_SECRET', usernameVariable: 'AWS_ACCESS')]) {
        
        // Retrieve the AWS ECR login password and login to the ECR
        sh "aws ecr get-login-password --region <your-region> | docker login --username AWS --password-stdin <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com"
        
        // Build the Docker image and tag it for ECR
        sh "docker build -t <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/demo-app:$IMAGE_NAME ."
        
        // Push the image to AWS ECR
        sh "docker push <aws-account-id>.dkr.ecr.<your-region>.amazonaws.com/demo-app:$IMAGE_NAME"

                        //for eks cradentials for acces docker hup >>
                 //  kubectl create secret docker-registry <secret-name> \
                //  --docker-username=<your-username> \
               //   --docker-password=<your-password> \
               //   --docker-email=<your-email> \
               //   --docker-server=<registry-server>
    }
}


def coomit_version(){
    withCredentials([usernamePassword(credentialsId: 'github_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'git config --global user.email "jenkins@example.com"'
        sh 'git config --global user.name "jenkins"'

        sh 'git status'
        sh 'git branch'
        sh 'git config --list'

        sh "git remote set-url origin https://${USER}:${PASS}@https://github.com/Walidbadry/java-maven-app.git"
        sh 'git add .'
        sh 'git commit -m "this is from jenkins version bumpe"'
        sh 'git push origin HEAD:increment_app_version'
    }
    

}
return this
