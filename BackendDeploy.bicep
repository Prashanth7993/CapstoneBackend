@description('AKS cluster name')
param aksName string

@description('AKS cluster resource group')
param aksResourceGroup string

@description('Helm release name')
param releaseName string

@description('Helm chart name')
param chartName string

@description('Helm chart version')
param chartVersion string

@description('Helm repo URL')
param chartRepo string

@description('Namespace to deploy chart')
param namespace string = 'default'

resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
  scope: resourceGroup(aksResourceGroup)
}

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
  name: releaseName
  scope: aks
  location: aks.location
  properties: {
    extensionType: 'kubernetesconfiguration'
    autoUpgradeMinorVersion: true
    releaseNamespace: namespace
    configurationSettings: {
      'helm.chart': chartName
      'helm.repository': chartRepo
      'helm.version': chartVersion
    }
  }
}
