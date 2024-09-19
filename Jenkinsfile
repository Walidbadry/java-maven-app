#!/user/bin/env groovy
//@Library('jenkins_shared_lib')
@Library(identifier: 'jenkins-shared-lib', retriever: modernSCM([
    $class: 'GitSCMSource',
    remote: 'https://github.com/YourRepo/jenkins-shared-library.git',
    credentialsId: 'githup_credintials'
])) _
def gv

pipeline {
    agent any
    environment{
        IMAGE= 'walid123321/java-app:$IMAGE_NAME'
    }
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
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    buildImage "env.IMAGE"
                    dockerLogin()
                    dockerPush "env.IMAGE"
                
                }
            }
        }
        stage('deploy repository') {
            steps {
                // Start the SSH agent and use the credentials ID
                sshagent(['ec2-server-key']) {
                    def shellcmd = "bash ./server_cmd.sh ${IMAGE}"
                    sh 'scp server_cmd.sh ec2-user@35.180.251.121:/home/ec2-user'
                    sh 'scp docker-compose.yaml ec2-user@35.180.251.121:/home/ec2-user'
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@35.180.251.121 ${shellcmd}"
                    // Run any SSH commands or clone repository via SSH
                    // sh 'git clone git@github.com:your-repository.git'

                    //you should configer ec2 rule
                }
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
