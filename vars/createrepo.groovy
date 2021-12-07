def call(place) {
  if (place.equals('github')) {
    pipeline {
      agent any
      environment {
      GitCred=credentials('github')
      }
      stages {
        stage('Even Stage') {
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
      stages {
        stage('Odd Stage') {
          steps {
            echo "The build number is odd"
          }
        }
      }
    }
  }
}
