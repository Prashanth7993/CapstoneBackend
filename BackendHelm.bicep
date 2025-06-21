param aksClusterName string
param aksResourceGroup string
param releaseName string
param namespace string = 'default'
param chartName string
param chartVersion string
param acrName string

resource aks 'Microsoft.ContainerService/managedClusters@2023-05-02-preview' existing = {
  name: aksClusterName
  scope: resourceGroup(aksResourceGroup)
}

module helmDeploy './BackendDeploy.bicep' = {
  name: 'helmDeployment'
  scope: aks
  params: {
    releaseName: releaseName
    namespace: namespace
    chartName: chartName
    chartVersion: chartVersion
    acrName: acrName
  }
}
