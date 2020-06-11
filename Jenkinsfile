pipeline {
    agent any

    tools {
    	maven 'maven'
	    jdk 'jdk'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		checkout([$class: 'GitSCM', branches: [[name: '*/many2many']],
			doGenerateSubmoduleConfigurations: false,
			extensions: [], submoduleCfg: [],
			userRemoteConfigs: [[credentialsId: 'Github',
			url: 'https://github.com/bestego/HueSim.git']]])
		sh "mvn -f huesim/pom.xml clean package -DskipTests"
            }
        }
         stage('JWT Token') {
             steps {
 				script {
           		withCredentials([usernamePassword(credentialsId: 'Portainer', usernameVariable: 'PORTAINER_USERNAME', passwordVariable: 'PORTAINER_PASSWORD')]) {
            			def json = """
                		{"Username": "$PORTAINER_USERNAME", "Password": "$PORTAINER_PASSWORD"}
             		"""
               		def jwtResponse = httpRequest acceptType: 'APPLICATION_JSON', contentType: 'APPLICATION_JSON', validResponseCodes: '200', httpMode: 'POST', ignoreSslErrors: true, consoleLogResponseBody: true, requestBody: json, url: "http://localhost:9000/api/auth"
               		def jwtObject = new groovy.json.JsonSlurper().parseText(jwtResponse.getContent())
               		env.JWTTOKEN = "Bearer ${jwtObject.jwt}"
           		}
         	}
         	echo "${env.JWTTOKEN}"
            }
        }

        stage('Deploy') {
            steps {
 		        script {
                 	echo 'Stopping container ....'
              		def stopResponse = httpRequest acceptType: 'APPLICATION_JSON', customHeaders:[[name:'Authorization', value:"${JWTTOKEN}"]], contentType: 'APPLICATION_JSON', validResponseCodes: '204,404', httpMode: 'POST', ignoreSslErrors: true, consoleLogResponseBody: false, url: "http://localhost:9000/api/endpoints/1/docker/containers/springboot/stop"

                 	echo 'Removing container ....'
              		def removeResponse = httpRequest acceptType: 'APPLICATION_JSON', customHeaders:[[name:'Authorization', value:"${JWTTOKEN}"]], contentType: 'APPLICATION_JSON', validResponseCodes: '204,404', httpMode: 'DELETE', ignoreSslErrors: true, consoleLogResponseBody: false, url: "http://localhost:9000/api/endpoints/1/docker/containers/springboot"

 			        echo 'Deleting image ....'
              		def removeImageResponse = httpRequest acceptType: 'APPLICATION_JSON', customHeaders:[[name:'Authorization', value:"${JWTTOKEN}"]], contentType: 'APPLICATION_JSON', validResponseCodes: '200,404', httpMode: 'DELETE', ignoreSslErrors: true, consoleLogResponseBody: false, url: "http://localhost:9000/api/endpoints/1/docker/images/springboot/springboot?force=true"

 		            echo 'Creating image ....'
 		            def dockerFile = 'huesim/src/main/docker/Dockerfile.java'
                    sh "docker build -f ${dockerFile} -t springboot/springboot ."

                	echo 'Creating container ....'
			        def json = """
			        { "name": "springboot", "Image": "springboot/springboot:latest", "Labels": { "docker-name": "springboot" }, "ExposedPorts": { "8080/tcp": {} }, "HostConfig": { "PortBindings": { "8080/tcp": [ { "HostIp": "", "HostPort": "8080" } ] }, "RestartPolicy": { "Name": "always" }, "NetworkMode": "host" } }
			        """
             		def createResponse = httpRequest acceptType: 'APPLICATION_JSON', customHeaders:[[name:'Authorization', value:"${JWTTOKEN}"]], contentType: 'APPLICATION_JSON', validResponseCodes: '200', httpMode: 'POST', ignoreSslErrors: true, consoleLogResponseBody: false, requestBody: json, url: "http://localhost:9000/api/endpoints/1/docker/containers/create?name=springboot"

                	echo 'Starting container ....'
             		def startResponse = httpRequest acceptType: 'APPLICATION_JSON', customHeaders:[[name:'Authorization', value:"${JWTTOKEN}"]], contentType: 'APPLICATION_JSON', validResponseCodes: '204', httpMode: 'POST', ignoreSslErrors: true, consoleLogResponseBody: false, url: "http://localhost:9000/api/endpoints/1/docker/containers/springboot/start"
 		        }
            }
        }
    }
}