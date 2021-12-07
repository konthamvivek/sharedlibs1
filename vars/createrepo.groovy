def call(place) {
  if (place.equals('github')) {
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
            curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$apiname'","private":true}'
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
            curl -X POST -v -u $GitCred_USR:$GitCred_PSW -H "Content-Type: application/json" https://api.bitbucket.org/2.0/repositories/$GitCred_USR/$apiname -d '{"scm": "git", "is_private": "true","project": {"key": "'$project_name'"} }'
            '''
            }
          }
        }
      }
    }
  }
}
