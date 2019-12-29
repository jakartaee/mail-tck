env.label = "mail-tck-ci-pod-${UUID.randomUUID().toString()}"
pipeline {
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  agent {
    kubernetes {
      label "${env.label}"
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
spec:
  hostAliases:
  - ip: "127.0.0.1"
    hostnames:
    - "localhost.localdomain"
    - "james.local"
  containers:
  - name: mail-tck-ci
    image: jakartaee/cts-javamail-base:0.1
    command:
    - cat
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "5Gi"
        cpu: "1"
  - name: james-mail
    image: jakartaee/cts-mailserver:0.1
    command:
    - cat
    env:
      - name: JAVA_TOOL_OPTIONS
        value: -Xmx1G
    ports:
    - containerPort: 1025
    - containerPort: 1143
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "2.5Gi"
        cpu: ".75"
"""
    }
  }
  parameters {
    string(name: 'JAF_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/activation/jakarta.activation/1.2.1/jakarta.activation-1.2.1.jar',
           description: 'URL required for downloading JAF implementation jar' )
    string(name: 'MAIL_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/mail/jakarta.mail/1.6.3/jakarta.mail-1.6.3.jar',
           description: 'URL required for downloading Javamail implementation jar' )
    string(name: 'TCK_BUNDLE_BASE_URL',
           defaultValue: '',
           description: 'Base URL required for downloading prebuilt binary TCK Bundle from a hosted location' )
    string(name: 'TCK_BUNDLE_FILE_NAME', 
           defaultValue: 'mail-tck-1.6.0.zip', 
	   description: 'Name of bundle file to be appended to the base url' )
    choice(name: 'LICENSE', choices: 'EPL\nEFTL',
           description: 'License file to be used to build the TCK bundle(s) either EPL(default) or Eclipse Foundation TCK License' )
  }
  environment {
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
    MAIL_USER="user01@james.local"
    MAIL_HOST="localhost"
  }
  stages {
    stage('mail-tck-build') {
      steps {
        container('mail-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_mailtck.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'mail-tck-bundles'
        }
      }
    }
  
    stage('mail-tck-run') {
      steps {
        container('james-mail') {
          sh """
            cd /root 
            /root/startup.sh | tee /root/mailserver.log &
            sleep 120
            bash -x /root/create_users.sh 2>&1 | tee /root/create_users.log
            echo "Mail server setup complete"
          """
	}
	container('mail-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_mailtck.sh
          """
          archiveArtifacts artifacts: "mail-tck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
