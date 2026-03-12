def call(String projectName = 'Sesion1 .NET Demo', boolean useProjectScripts = true) {
  stage('Checkout App') {
    checkout scm
  }

  def scriptsDir = useProjectScripts ? 'scripts' : 'pipeline-remote/scripts'

  stage('Build') {
    powershell "./${scriptsDir}/buildremote.ps1 -ProjectName '${projectName}'"
  }
  stage('Test') {
    powershell "./${scriptsDir}/testremote.ps1 -ProjectName '${projectName}'"
  }
  stage('Notify') {
    powershell "./${scriptsDir}/notifyremote.ps1 -ProjectName '${projectName}' -Status 'SUCCESS'"
  }
}

return this

