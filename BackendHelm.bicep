@description('AKS Cluster Name')
param aksClusterName string

@description('Resource Group of the AKS Cluster')
param aksResourceGroup string

@description('Namespace to install the Helm chart')
param namespace string = 'default'

@description('Helm Release Name')
param releaseName string = 'myapp-release'

@description('Name of the Helm chart in ACR')
param chartName string

@description('Version of the Helm chart')
param chartVersion string = '0.1.0'

@description('ACR name (without .azurecr.io)')
param acrName string

resource aks 'Microsoft.ContainerService/managedClusters@2023-05-02-preview' existing = {
  name: aksClusterName
  scope: resourceGroup(aksResourceGroup)
}

resource helmChart 'Microsoft.KubernetesConfiguration/helmCharts@2022-11-01' = {
  name: releaseName
  parent: aks
  properties: {
    chartName: chartName
    chartVersion: chartVersion
    repositoryUrl: 'oci://${acrName}.azurecr.io/helm'
    scope: {
      namespace: namespace
      releaseNamespace: namespace
    }
  }
}
