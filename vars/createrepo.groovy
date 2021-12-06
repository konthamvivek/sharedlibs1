def repo(String name){
  if name.equals("github"){
  sh '''
    ls -la
    curl -u $GitCred_USR:$GitCred_PSW https://api.github.com/user/repos -d '{"name":"'$apiname'","private":true}'
    
  '''
}
  else if name.equals("bitbucket"){

  sh '''
  
  curl -X POST -v -u $GitCred_USR:$GitCred_PSW -H "Content-Type: application/json" https://api.bitbucket.org/2.0/repositories/$GitCred_USR/$apiname -d '{"scm": "git", "is_private": "true","project": {"key": "'JEN'"} }'
  
  '''
}
  
}
