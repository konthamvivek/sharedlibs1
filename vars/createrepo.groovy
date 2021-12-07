def call(SCM) {
  if (SCM.equals('github')) {
    pipeline {
      agent any
      environment {
      GitCred=credentials('github')
      }
      stages {
        stage('github') {
          steps {
            script{
              sh '''
            curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$ApiName'","private":true}'
            '''  
            }
          }
        }
      }
    }
  } else {
    pipeline {
      agent any
      environment {
      GitCred=credentials('bbtdarshan')
      }
      stages {
        stage('bitbucket') {
          steps {
            script{
              sh '''
            curl -X POST -v -u $GitCred_USR:$GitCred_PSW -H "Content-Type: application/json" https://api.bitbucket.org/2.0/repositories/$GitCred_USR/$ApiName -d '{"scm": "git", "is_private": "true","project": {"key": "'$ProjectName'"} }'
            '''
            }
          }
        }
      }
    }
  }
}
