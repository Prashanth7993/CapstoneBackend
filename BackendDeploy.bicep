param aksName string
param aksResourceGroup string
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string = 'backend'

// Get AKS cluster as existing resource
resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName
  scope: resourceGroup(aksResourceGroup)
}

// Use module to deploy helm
module helmDeploy 'BackendHelm.bicep' = {
  name: 'helmDeployToAKS'
  scope: aks
  params: {
    releaseName: releaseName
    chartName: chartName
    chartVersion: chartVersion
    chartRepo: chartRepo
    namespace: namespace
  }
}
