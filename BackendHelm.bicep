param aksClusterName string
param aksResourceGroup string
param releaseName string = 'myapp'
param namespace string = 'default'
param chartName string
param chartVersion string = '0.1.0'
param acrName string

resource aks 'Microsoft.ContainerService/managedClusters@2023-05-02-preview' existing = {
  name: aksClusterName
  scope: resourceGroup(aksResourceGroup)
}

module helmDeploy 'BackendHelm.bicep' = {
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
