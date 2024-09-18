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
                    buildImage 'my_app:1.1'
                    dockerLogin()
                    dockerPush 'my_app:1.1'
                
                }
            }
        }
        stage('deploy repository') {
            steps {
                def DockerCmd = 'docker run -d -p 3000:3080 walid123321/demo-app:1.1'
                // Start the SSH agent and use the credentials ID
                sshagent(['ec2-server-key']) {
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@35.180.251.121 ${DockerCmd}"
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
