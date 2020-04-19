podTemplate(label: 'swaglist-ci',
    containers: [
        containerTemplate(name: 'java-node', image: 'timbru31/java-node', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'node',
                          image: 'node:10.16.0',
                          ttyEnabled: true,
                          command: 'cat',
                          resourceRequestCpu: '400m',
                          resourceRequestMemory: '512Mi'),
        containerTemplate(name: 'maven',
                          image: 'maven:3.3-jdk-8',
                          ttyEnabled: true,
                          command: 'cat',
                          resourceRequestCpu: '400m',
                          resourceRequestMemory: '512Mi'),
        containerTemplate(name: 'docker',
                          image: 'docker:1.11',
                          ttyEnabled: true,
                          command: 'cat'),
        containerTemplate(name: 'kubectl',
                          image: 'lachlanevenson/k8s-kubectl',
                          command: 'cat',
                          ttyEnabled: true),
        containerTemplate(name: 'helm',
                          image: 'lachlanevenson/k8s-helm:v2.16.1',
                          command: 'cat',
                          ttyEnabled: true)
    ],
    volumes: [
        hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
        hostPathVolume(hostPath: '/dev/shm', mountPath: '/dev/shm')
    ],
    nodeSelector: 'internal-role: worker'
) {

    def tag = "${BRANCH_NAME}".toLowerCase().replaceAll("_", "-").replaceAll("/", "-")
    def namespace = "swaglist-" + tag

    def imageBackend = "swaglist/backend:" + tag
    def imageFrontend = "swaglist/frontend:" + tag
    def backendService = "backend." + namespace + ".svc.cluster.local"

    def frontendPort = "0"

    def message = "No environment is created"
    def messageColor = "#4a90e2"

    node('swaglist-ci') {

        properties([
            disableConcurrentBuilds(),
            buildDiscarder(logRotator(numToKeepStr: '25', artifactNumToKeepStr: '25'))
        ])

        withCredentials([
            string(credentialsId: 'docker-registry-url', variable: 'DOCKER_REGISTRY_URL'),
            string(credentialsId: 'sonarqube-token', variable: 'SONARQUBE_TOKEN'),
            file(credentialsId: 'swaglist_config', variable: 'KUBECONFIG'),
            file(credentialsId: 'npmrc', variable: 'NPMRC'),
]) {

            try {

                stage('checkout') {
                    container('maven') {
                        checkout scm
                    }
                }

                parallel(
                    Frontend: {
                        dir('frontend') {

                            stage('install') {
                                container('node') {
                                    sh "cp '${NPMRC}' .npmrc"
                                    sh "npm ci"
                                }
                            }

                            stage('lint') {
                                container('node') {
                                    sh "npm run lint -- --format json > tslint-report.txt"
                                    sh "tail -n +5 tslint-report.txt > tslint-report.json"
                                    sh "rm tslint-report.txt"
                                }
                            }

                            stage('test') {
                                container('chrome') {
                                    sh "npm run test -- --codeCoverage=true --browsers HeadlessChrome"
                                }
                            }

                            stage('build') {
                                container('node') {
                                    sh "node build --prod"
                                }
                            }

                            stage('container') {
                                container('docker') {
                                    sh "sed -i 's/backend/${backendService}/g' docker_build/nginx.conf"
                                    sh "mv dist/ docker_build/"
                                    sh "docker build -t ${imageFrontend} docker_build"
                                    sh "docker tag ${imageFrontend} ${DOCKER_REGISTRY_URL}/${imageFrontend}"
                                    sh "docker push ${DOCKER_REGISTRY_URL}/${imageFrontend}"
                                }
                            }
                        }
                    },

                    Backend: {
                        dir('backend') {

                            stage('test') {


                                container('maven') {
                                    sh "mvn clean test"
                                }
                                junit '**/target/test-results/test/*.xml'
                            }

                            stage('build') {
                                container('maven') {
                                    sh "mvn clean package"
                                }
                            }

                            stage('container') {
                                container('docker') {
                                    sh "mv build/libs/backend-*.jar docker_build/backend.jar"
                                    sh "docker build -t ${imageBackend} docker_build"
                                    sh "docker tag ${imageBackend} ${DOCKER_REGISTRY_URL}/${imageBackend}"
                                    sh "docker push ${DOCKER_REGISTRY_URL}/${imageBackend}"
                                }
                            }
                        }
                    }
                )

                stage('deploy') {
                    def isEnvironmentExists = false

                    container('helm') {
                        sh "mkdir /root/.kube"
                        sh "cp '${KUBECONFIG}' /root/.kube/config"

                        isEnvironmentExists = sh (
                            script: 'echo $(k8s/helm/isEnvironmentExists.sh ' + namespace + ')',
                            returnStdout: true
                        ).trim()
                    }

                    if (isEnvironmentExists == 'true') {
                        sh "echo 'Environment exists on port'"

                        container('kubectl') {
                            frontendPort = sh (script: 'echo $(kubectl get svc frontend -n ' + namespace + ' -o=jsonpath="{.spec.ports[?(@.port==80)].nodePort}")',
                                returnStdout: true
                            ).trim()
                        }

                        container('kubectl') {
                            sh "echo 'Updating environment'"
                            sh 'kubectl patch deployment -n ' + namespace + ' backend -p "{\\"spec\\": {\\"template\\": {\\"metadata\\": { \\"labels\\": {  \\"redeploy\\": \\"$(date +%s)\\"}}}}}"'
                            sh 'kubectl patch deployment -n ' + namespace + ' frontend -p "{\\"spec\\": {\\"template\\": {\\"metadata\\": { \\"labels\\": {  \\"redeploy\\": \\"$(date +%s)\\"}}}}}"'
                            sleep 10
                        }
                    } else {
                        container('helm') {
                            sh "echo 'Creating new environment'"
                            sh "helm install --namespace '${namespace}' k8s/helm/swaglist --set tag='${tag}',frontendService.nodePort='${frontendPort}' --name '${namespace}'"
                        }
                    }

                    container('kubectl') {
                        frontendPort = sh (script: 'echo $(kubectl get svc frontend -n ' + namespace + ' -o=jsonpath="{.spec.ports[?(@.port==80)].nodePort}")',
                            returnStdout: true
                        ).trim()
                    }

                    message = "[http://192.168.178.20:" + frontendPort + "](http://192.168.178.20:" + frontendPort + ")<br>"
                }

                currentBuild.result = 'SUCCESS'
            }
            catch (Exception error) {
                currentBuild.result = 'FAILURE'
                messageColor = "#d54c53"
                throw error
            }
            finally {

            }

        }
    }
}
