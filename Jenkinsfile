pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package -X'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                input message: 'Start deploy? (Click "Proceed" to continue)'
                sh './jenkins/scripts/deliver.sh'
                timeout(time: 1, unit: 'MINUTES'){
                sh "chmod +x -R ${env.WORKSPACE}"
                sh './kill.sh'
                }
               
            }
        }
    }
}
