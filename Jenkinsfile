def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }

        stage('increment version'){
                steps{
                    script{
                        gv.incremint_ver()
                        
                    }
                }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    gv.buildImage()
                }
            }
        }
        stage('deploy repository to eks') {
            environment{
                AWS_ACCES_KEY_ID = cradentials('jenkins-aws-access-key_id') 
                AWS_SECRET_ACCESS_KEY = cradentials('jenkins-aws-secret-access_key')
                APP_NAME = 'JAVA_MAVEN_APP'
            }
            steps {
                echo "deploy docker image"
                sh "envsubset <deployment.yaml | kupectl apply -f -"
                sh "envsubset <service.yaml | kupectl apply -f -"
                //for eks cradentials for acces docker hup >>
                 //  kubectl create secret docker-registry <secret-name> \
                //  --docker-username=<your-username> \
               //   --docker-password=<your-password> \
               //   --docker-email=<your-email> \
               //   --docker-server=<registry-server>
             
                //you shold install envsubset for passing invironment inside docker > apt-get install gettext-base
            }
        }

        stage("commit version update") {
            steps {
                script {
                    gv.coomit_version()

                }
            }
        }
        
    }   
}
