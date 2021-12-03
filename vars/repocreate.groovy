def  repocreate(){
    sh '''
    curl -X POST -v -u $GitCred_USR:$GitCred_PSW -H "Content-Type: application/json" https://api.bitbucket.org/2.0/repositories/$GitCred_USR/$apiname -d '{"scm": "git", "is_private": "true","project": {"key": "'JEN'"} }'   
    '''
}
