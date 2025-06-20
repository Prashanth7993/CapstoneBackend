targetScope = 'subscription'

@description('Name of the AKS Cluster')
param aksName string

@description('Resource Group of the AKS Cluster')
param aksResourceGroup string

@description('Helm release name')
param releaseName string

@description('Helm chart name')
param chartName string

@description('Helm chart version')
param chartVersion string

@description('Helm chart repo (OCI or HTTP)')
param chartRepo string

@description('Namespace to install the release into')
param namespace string = 'backend'

// Reference the existing AKS cluster
resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
  scope: resourceGroup(aksResourceGroup)
}

// Deploy the Helm chart to the AKS cluster
resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
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
