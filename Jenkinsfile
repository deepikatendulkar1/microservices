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
                    // Bypass Docker Pipeline plugin method and use shell to check Docker version
                    sh 'docker --version'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Docker Pipeline plugin
                    def cacheBust = System.currentTimeMillis() // Cache busting
                    dockerImage = docker.build("${registry}:${env.BUILD_NUMBER}", "--build-arg CACHEBUST=${cacheBust} .")
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    // Push the Docker image using the Docker Pipeline plugin
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
                    // Use the GCP service account to authenticate with Google Cloud
                    withCredentials([file(credentialsId: gcpServiceAccount, variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        // Configure the GKE plugin to authenticate with GCP and deploy to GKE
                        gkeCluster(name: 'cluster-1', projectId: gcpProject, zone: 'us-central1-c', credentialsId: gcpServiceAccount) {
                            kubernetesDeploy(
                                kubeconfigId: 'gcp-k8s-key',
                                configs: ['deployment.yaml', 'service.yaml'],
                                enableConfigSubstitution: true
                            )
                        }
                    }
                }
            }
        }
    }
}
