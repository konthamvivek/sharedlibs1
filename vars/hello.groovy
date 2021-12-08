def call(SCM) {
  if (SCM.equals('github')) { //if the parameter SCM is equal to github it will create github repository
    pipeline {
      agent any
      //mention the agent if not give any
      environment {
      GitCred=credentials('github')
      //storing the github credentials in GitCred, we can access username and password as GitCred_USR and GitCred_PSW respectively  
      }
      stages {
        stage('github') {
          steps {
            script{
              //github api (curl command to create github repository)
              sh '''
            curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$ApiName'","private":true}'
            pwd 
            mkdir $ApiName
            cd $ApiName
            git init
             
            git remote add $ApiName https://github.com/$GitCred_USR/$ApiName.git
//             git checkout -b main
//             git push https://$GitCred_USR:$GitCred_PSW@github.com/$GitCred_USR/$ApiName.git main
            
            git checkout -b develop
            git push https://${GitCred_USR}:${GitCred_PSW}@github.com/${GitCred_USR}/${ApiName}.git develop
            
           
            '''  
            }
          }
        }
      }
    }
  } else { //if the parameter SCM is not equal to github it will create bitbucket repository
    pipeline {
      agent any
      environment {
      GitCred=credentials('bbtdarshan')
        //storing the Bitbucket credentials in GitCred, we can access username and password as GitCred_USR and GitCred_PSW respectively 
      }
      stages {
        stage('bitbucket') {
          steps {
            script{
              //bitbucket api (curl command to create github repository)
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

