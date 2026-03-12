// Remote pipeline step library (loaded via `load`)
// Usage from Jenkinsfile:
// def remote = load 'pipelines/buildAndTest.groovy'
// remote.call('My Project', true)

def call(String projectName = 'Sesion1 .NET Demo', boolean useProjectScripts = True) {
  node {
    stage('Checkout App') {
      checkout scm
    }

    def scriptsDir = useProjectScripts ? 'scripts' : 'pipeline-remote/scripts'

    stage('Build') {
      def ws = pwd()  
      bat "dir"
      powershell "./${scriptsDir}/buildremote.ps1 -ProjectName '${projectName}'"
    }
    stage('Test') {
      powershell "./${scriptsDir}/testremote.ps1 -ProjectName '${projectName}'"
    }
    stage('Notify') {
      powershell "./${scriptsDir}/notifyremote.ps1 -ProjectName '${projectName}' -Status 'SUCCESS'"
    }
  }
}

return this





