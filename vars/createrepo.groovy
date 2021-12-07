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
            echo "The build number is even"
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
