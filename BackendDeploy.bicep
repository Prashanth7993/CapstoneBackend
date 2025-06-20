@description('Name of the AKS cluster')
param aksName string

@description('Resource group of the AKS cluster')
param aksResourceGroup string

@description('Helm release name')
param releaseName string

@description('Helm chart name')
param chartName string

@description('Helm chart version')
param chartVersion string

@description('Helm chart repository URL')
param chartRepo string

@description('Kubernetes namespace for deployment')
param namespace string = 'backend'

resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
  scope: resourceGroup(aksResourceGroup)
}

resource helmRelease 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
  name: releaseName
  scope: aks
  properties: {
    extensionType: 'helm'
    releaseTrain: 'stable'
    version: chartVersion
    autoUpgradeMinorVersion: false
    scope: {
      cluster: {
        releaseNamespace: Default
      }
    }
    configurationSettings: {
      chart: chartName
      repository: chartRepo
    }
  }
}
