pipeline {
    agent any
    stages {
        stage('Test Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker_docker', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    bat "echo %PASS% | docker login -u %USER% --password-stdin"
                }
            }
        }
    }
}
