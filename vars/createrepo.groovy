
def create(String place){
  if ($place.equals("bitbucket")) {
    pipeline {
      agent any
      environment{
          GitCred=credentials('bbtdarshan')
      }
      stages {
        stage('bitbucket') {
          steps {
            script{
            echo "The build number is for bitbucket"
              sh '''
  
              curl -X POST -v -u $GitCred_USR:$GitCred_PSW -H "Content-Type: application/json" https://api.bitbucket.org/2.0/repositories/$GitCred_USR/$apiname -d '{"scm": "git", "is_private": "true","project": {"key": "'$project_name'"} }'
  
               '''
            }
          }
        }
      }
    }
  }
  else {
    pipeline {
      agent any
        environment{
          GitCred=credentials('github')
      }
      stages {
        stage('github') {
          steps {
            echo "The build number is for github"
              sh '''
                ls -la
                curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$apiname'","private":true}'
    
                '''
          }
        }
      }
    }
  }
}
