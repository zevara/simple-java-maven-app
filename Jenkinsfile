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
                    sh "scp -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -r  /var/jenkins_home/workspace/submission-cicd-pipeline-kawainekodesu ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com:/tmp/"
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'cd /tmp/submission-cicd-pipeline-kawainekodesu && ./jenkins/scripts/deliver.sh'"
                    sh "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ubuntu@ec2-54-169-222-38.ap-southeast-1.compute.amazonaws.com 'cd /tmp/submission-cicd-pipeline-kawainekodesu && ./jenkins/scripts/kill.sh'"    
                // sh './jenkins/scripts/deliver.sh'
                // sh 'chmod +x ./jenkins/scripts/kill.sh'
                // sh './jenkins/scripts/kill.sh'             
                   
            }
            }
        }
    }
}
