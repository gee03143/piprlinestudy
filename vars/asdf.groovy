properties([parameters([string(defaultValue: 'test', description: 'test or verify', name: 'testType', trim: false)])])

node {
    checkout scm
    
    stage('build') {
        //withEnv(["PATH+MAVEN=${tool 'mvn-3.3.9'}/bin"]) {
        //withEnv(["PATH+MAVEN=${tool 'mvn-3.6.2'}/bin"]) {
        withEnv(["PATH+MAVEN=${tool 'mvn-3.6.0'}/bin"]) {
            sh 'mvn --version'
            sh "mvn clean ${params.testType}"
        }
    }
    
    stage('report') {
        junit 'target/surefire-reports/*.xml'
        jacoco execPattern: 'target/**.exec'
        emailext body: 'asdf', subject: 'asdf', to: 'gee03143@naver.com'
    }
}