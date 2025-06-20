targetScope = 'subscription'

param aksName string
param aksResourceGroup string
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string = 'backend'

// Reference existing AKS cluster
resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
  scope: resourceGroup(aksResourceGroup)
}

resource helmExt 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
  name: releaseName
  scope: aks
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
