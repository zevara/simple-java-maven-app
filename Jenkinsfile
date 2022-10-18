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
                sshagent (credentials: ['ubuntu']) {
                    input message: 'Start deploy? (Click "Proceed" to continue)'
                    sh "apt update && apt install zip -y"
                    sh "zip -r submission.zip ."
                    sh "scp -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -r submission.zip ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com:/tmp/"
                    // sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'git clone https://github.com/zevara/simple-java-maven-app.git'"
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'cd /tmp/ && unzip submission.zip'"
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'cd /tmp/submission-cicd-pipeline-kawainekodesu && ./jenkins/scripts/deliver.sh'"
                    // sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'cd /tmp/submission-cicd-pipeline-kawainekodesu && ./jenkins/scripts/kill.sh'"    
                // sh './jenkins/scripts/deliver.sh'
                // sh 'chmod +x ./jenkins/scripts/kill.sh'
                // sh './jenkins/scripts/kill.sh'             
                   
            }
            }
        }
    }
}
