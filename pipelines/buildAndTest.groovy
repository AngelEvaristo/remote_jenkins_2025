// Remote pipeline step library (loaded via `load`)
// Usage from Jenkinsfile:
// def remote = load 'pipelines/buildAndTest.groovy'
// remote.call('My Project', true)

def call(String projectName = 'Sesion1 .NET Demo', boolean useProjectScripts = true) {
  node {
    stage('Checkout App') {
      checkout scm
    }

    def scriptsDir = useProjectScripts ? 'scripts' : 'pipeline-remote/scripts'

    stage('Build') {
      powershell "./${scriptsDir}/build.ps1 -ProjectName '${projectName}'"
    }
    stage('Test') {
      powershell "./${scriptsDir}/test.ps1 -ProjectName '${projectName}'"
    }
    stage('Notify') {
      powershell "./${scriptsDir}/notify.ps1 -ProjectName '${projectName}' -Status 'SUCCESS'"
    }
  }
}

return this
