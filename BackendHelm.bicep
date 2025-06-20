targetScope = 'resource'
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string
param aksName string
param aksResourceGroup string

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
  name: releaseName
  scope: resource('Microsoft.ContainerService/managedClusters', aksName)
  parent: resourceGroup(aksResourceGroup)
  properties: {
    extensionType: 'helm'
    releaseTrain: 'stable'
    autoUpgradeMinorVersion: false
    version: chartVersion
    scope: {
      cluster: {
        releaseNamespace: namespace
      }
    }
    configurationSettings: {
      chart: chartName
      repository: chartRepo
    }
  }
}
