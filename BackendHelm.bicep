param aksClusterName string
param aksResourceGroup string
param releaseName string = 'myapp'
param namespace string = 'default'

param acrName string
param chartName string
param chartVersion string = '0.1.0'

resource aks 'Microsoft.ContainerService/managedClusters@2023-05-02-preview' existing = {
  name: aksClusterName
  scope: resourceGroup(aksResourceGroup)
}

resource helmRelease 'Microsoft.KubernetesConfiguration/helmCharts@2022-11-01' = {
  name: releaseName
  scope: extensionResourceId(aks.id, 'Microsoft.Kubernetes/connectedClusters', 'default') // adjust as needed

  properties: {
    chartName: chartName
    chartVersion: chartVersion
    repositoryUrl: 'oci://'+acrName+'.azurecr.io/helm'
    scope: {
      namespace: namespace
      releaseNamespace: namespace
    }
  }
}
