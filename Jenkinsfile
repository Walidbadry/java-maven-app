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
                    // buildImage 'my_app:1.1'
                   //  dockerLogin()
                  //   dockerPush 'my_app:1.1'
                
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
