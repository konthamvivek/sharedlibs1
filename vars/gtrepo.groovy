def gtcreate(){
  sh '''
  
    curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$apiName'","private":true}'
    
  '''
}