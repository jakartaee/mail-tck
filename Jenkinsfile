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
        memory: "1Gi"
        cpu: ".75"
"""
    }
  }
  parameters {
    choice(name: 'JDK', choices: 'JDK8\nJDK11',
           description: 'Java SE Version to be used for running TCK either JDK8/JDK11' )
    choice(name: 'LICENSE', choices: 'EPL\nEFTL',
           description: 'License file to be used to build the TCK bundle(s) either EPL(default) or Eclipse Foundation TCK License' )
  }
  environment {
    MAIL_USER="user01@james.local"
    MAIL_HOST="localhost"
  }
  stages {
    stage('mail-tck-run') {
      steps {
        container('james-mail') {
          sh """
            cd /root 
            /root/startup.sh | tee /root/mailserver.log &
            sleep 120
            bash -x /root/create_users.sh 2>&1 | tee /root/create_users.log
            echo "Mail server setup complete"
            env
            export TCK_ZIP=https://github.com/jbescos/mail-tck/archive/refs/heads/junit5.zip
            wget $TCK_ZIP -O mail-tck.zip
            unzip mail-tck.zip -d mail-tck
            cd mail-tck/*/
            bash -x docker/run_mailtck.sh
          """
        }
      }
    }
  }
}
