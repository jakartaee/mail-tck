env.label = "javamail-ci-pod-${UUID.randomUUID().toString()}"
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
  - name: javamail-ci
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
    image: linagora/james-jpa-sample:3.0.1
    command:
    - /root/startup.sh
    ports:
    - containerPort: 25
    - containerPort: 143
    tty: true
    imagePullPolicy: Always
    resources:
      limits:
        memory: "2Gi"
        cpu: "0.5"
"""
    }
  }
  parameters {
    string(name: 'JAF_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/activation/javax.activation/1.2.0/javax.activation-1.2.0.jar',
           description: 'URL required for downloading JAF implementation jar' )
    string(name: 'JAVAMAIL_BUNDLE_URL',
           defaultValue: 'http://central.maven.org/maven2/com/sun/mail/javax.mail/1.6.1/javax.mail-1.6.1.jar',
           description: 'URL required for downloading JAF implementation jar' )
    string(name: 'httpProxyHost',
           defaultValue: '',
           description: 'Proxy host for connecting to http urls')
    string(name: 'httpProxyPort',
           defaultValue: '',
           description: 'Proxy port for connecting to http urls')
    string(name: 'httpsProxyHost',
           defaultValue: '',
           description: 'Proxy host for connecting to https urls')
    string(name: 'httpsProxyPort',
           defaultValue: '',
           description: 'Proxy port for connecting to https urls')

  }
  environment {
    http_proxy = ""
    https_proxy = ""
    ANT_OPTS = "-Djavax.xml.accessExternalStylesheet=all -Djavax.xml.accessExternalSchema=all -Djavax.xml.accessExternalDTD=file,http" 
    MAIL_USER="user01@james.local"
  }
  stages {
    stage('javamail-build') {
      steps {
        container('javamail-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/build_javamail.sh
          """
          archiveArtifacts artifacts: 'bundles/*.zip'
          stash includes: 'bundles/*.zip', name: 'javamail-bundles'
        }
      }
    }
  
    stage('javamail-run') {
      steps {
        container('javamail-ci') {
          sh """
            env
            bash -x ${WORKSPACE}/docker/run_javamail.sh
          """
          archiveArtifacts artifacts: "javamailtck-results.tar.gz"
          junit testResults: 'results/junitreports/*.xml', allowEmptyResults: true
        }
      }
    }
  }
}
