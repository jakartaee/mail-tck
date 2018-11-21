env.label = "javamail-tck-ci-pod-${UUID.randomUUID().toString()}"
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
  - name: javamail-tck-ci
    image: anajosep/cts-base:0.1
    command:
    - cat
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "6Gi"
        cpu: "1.25"
  - name: james-mail
    image: anajosep/cts-mailserver:0.1
    command:
    - /root/startup.sh
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
        memory: "4Gi"
        cpu: "1.0"
"""
    }
  }
  parameters {
    string(name: 'JAF_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/activation/javax.activation/1.2.0/javax.activation-1.2.0.jar',
           description: 'URL required for downloading JAF implementation jar' )
    string(name: 'JAVAMAIL_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/mail/javax.mail/1.6.1/javax.mail-1.6.1.jar',
           description: 'URL required for downloading Javamail implementation jar' )
  }
  environment {
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
    MAIL_USER="user01@james.local"
  }
  stages {
    stage('javamail-tck-build') {
      steps {
        container('javamail-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_javamailtck.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'javamail-tck-bundles'
        }
      }
    }
  
    stage('javamail-tck-run') {
      steps {
        container('javamail-tck-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_javamailtck.sh
          """
          archiveArtifacts artifacts: "javamailtck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
