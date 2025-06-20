param aksName string
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string

resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
}

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-11-01' = {
  name: releaseName
  // REMOVE THE 'parent' PROPERTY HERE
  // parent: aks // THIS LINE SHOULD BE DELETED
  scope: resourceGroup().id // Explicitly define the scope if needed, or rely on the module's scope
  properties: {
    extensionType: 'kubernetesConfiguration'
    autoUpgradeMinorVersion: true
    releaseTrain: 'Stable'
    version: chartVersion
    scope: {
      cluster: {
        releaseNamespace: namespace
      }
    }
    configurationSettings: {
      chartName: chartName // Removed quotes
      repositoryUrl: chartRepo // Removed quotes
    }
  }
}