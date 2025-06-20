param cluster1Name string = 'aks-cluster1'
param cluster2Name string = 'aks-cluster2'
param location string = resourceGroup().location
param helmChartName string = 'backend'
param helmChartVersion string = '1.0.0'
param helmRepoUrl string = 'oci://mysharedacr1234.azurecr.io/helm'
param namespace string = 'default'
param releaseName string = 'my-backend-release'

// Reference AKS Cluster 1
resource aksCluster1 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: cluster1Name
}

// Reference AKS Cluster 2
resource aksCluster2 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: cluster2Name
}

// Helm Deployment to Cluster 1
resource helmReleaseCluster1 'Microsoft.KubernetesConfiguration/extensions@2023-05-01' = {
  name: '${releaseName}-helm-cluster1'
  scope: resourceGroup()
  properties: {
    extensionType: 'microsoft.helm'
    autoUpgradeMinorVersion: true
    configurationSettings: {
      'helm.operator.enabled': 'true'
      'helm.operator.chartName': helmChartName
      'helm.operator.chartVersion': helmChartVersion
      'helm.operator.repository': helmRepoUrl
      'helm.operator.namespace': namespace
      'helm.operator.releaseName': releaseName
    }
    scope: aksCluster1.id
  }
}

// Helm Deployment to Cluster 2
resource helmReleaseCluster2 'Microsoft.KubernetesConfiguration/extensions@2023-05-01' = {
  name: '${releaseName}-helm-cluster2'
  scope: resourceGroup()
  properties: {
    extensionType: 'microsoft.helm'
    autoUpgradeMinorVersion: true
    configurationSettings: {
      'helm.operator.enabled': 'true'
      'helm.operator.chartName': helmChartName
      'helm.operator.chartVersion': helmChartVersion
      'helm.operator.repository': helmRepoUrl
      'helm.operator.namespace': namespace
      'helm.operator.releaseName': releaseName
    }
    scope: aksCluster2.id
  }
}