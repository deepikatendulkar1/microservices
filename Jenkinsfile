//Deepika Tendulkar , Sanjana Vegesna
//Clones a repository, checks Docker version, builds and pushes a Docker image to Docker Hub with versioning, and deploys it to GKE using GCP credentials.
pipeline {
    agent any
    environment {
        registry = "sanjanavegesna/student-survey-app"
        registryCredential = 'dockerhub-creds'
        gcpProject = 'sixth-foundry-442718-e2'
        gcpServiceAccount = 'gcp-k8s-key'
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/deepikatendulkar1/microservices'
            }
        }
        stage('Check Docker Version') {
            steps {
                script {
                    sh 'docker --version'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Add cache busting to ensure files are updated
                    def cacheBust = System.currentTimeMillis() // Use current timestamp as cache buster
                    dockerImage = docker.build("${registry}:${env.BUILD_NUMBER}", "--build-arg CACHEBUST=${cacheBust} .")
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', registryCredential) {
                        dockerImage.push("latest")
                        dockerImage.push("${env.BUILD_NUMBER}")
                    }
                }
            }
        }
        stage('Deploy to GKE') {
            steps {
                script {
                    withCredentials([file(credentialsId: gcpServiceAccount, variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh '''
                        export GOOGLE_APPLICATION_CREDENTIALS=$GOOGLE_APPLICATION_CREDENTIALS
                        gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
                        gcloud config set project $gcpProject  # Set the GCP project
                        gcloud container clusters get-credentials cluster-1 --zone us-central1-c
                        
                        # Replace image tag in deployment.yaml with the build number
                        sed -i "s|image: sanjanavegesna/student-survey-app:v1|image: sanjanavegesna/student-survey-app:${BUILD_NUMBER}|g" deployment.yaml
                        
                        # Apply the deployment and service configurations
                        kubectl apply -f deployment.yaml || { echo 'Deployment failed' ; exit 1; }
                        kubectl apply -f service.yaml || { echo 'Service application failed' ; exit 1; }
                        
                        # Delete pods to force new deployment with updated image
                        kubectl delete pods -l app=student-survey-app || { echo 'Failed to delete pods' ; exit 1; }
                        '''
                    }
                }
            }
        }
    }
}
