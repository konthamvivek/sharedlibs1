def hello(String firstname){
  sh '''
  echo hello world '$firstname'
  '''
}
