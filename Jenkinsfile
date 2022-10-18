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
        stage('Manual Approval'){
            steps{
                input message: 'Start deploy? (Click "Proceed" to continue)'
            }
        }
        stage('Deploy') {
            steps {
                sshagent (credentials: ['ubuntu']) {
                    sh './jenkins/scripts/deliver.sh'
                    sh 'chmod +x ./jenkins/scripts/kill.sh'
                    sh './jenkins/scripts/kill.sh'          
                    sh "scp -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -r  /var/jenkins_home/workspace/submission-cicd-pipeline-kawainekodesu/target/ ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com:/tmp/"
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'java -jar /tmp/target/my-app-1.0-SNAPSHOT.jar & '" 
                 
                   
            }
            }
        }
    }
}
