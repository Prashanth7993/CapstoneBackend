@description('Name of the AKS cluster')
param aksClusterName string

@description('Resource group of the AKS cluster')
param aksClusterResourceGroup string

@description('Namespace where the Helm chart should be installed')
param namespace string = 'default'

@description('Name of the Helm release')
param releaseName string

@description('Repository URL of the Helm chart')
param repoUrl string

@description('Name of the Helm chart')
param chartName string

@description('Version of the Helm chart')
param chartVersion string = 'latest'

@description('Optional: Values.yaml override')
param values string = ''

// Import AKS cluster
resource aks 'Microsoft.ContainerService/managedClusters@2023-05-02-preview' existing = {
  name: aksClusterName
  scope: resourceGroup(aksClusterResourceGroup)
}

// Link Kubernetes extension
resource helmChart 'Microsoft.KubernetesConfiguration/helmCharts@2022-11-01' = {
  name: releaseName
  scope: extensionResourceId(aks.id, 'Microsoft.Kubernetes/connectedClusters', 'default') // use 'connectedClusters' if using Arc, or use 'managedClusters' for AKS
  properties: {
    chartName: chartName
    chartVersion: chartVersion
    repositoryUrl: repoUrl
    scope: {
      namespace: namespace
      releaseNamespace: namespace
    }
    values: values
  }
}
