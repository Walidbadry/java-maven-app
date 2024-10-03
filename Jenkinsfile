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
    stages {
        stage('Test Docker Login') {
                    echo "building jar"
                    gv.buildJar()

            }
        }
    }
}
