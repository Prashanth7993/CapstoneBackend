@description('AKS cluster name')
param aksName string

@description('Resource group where AKS is deployed')
param aksResourceGroup string

@description('Helm release name')
param releaseName string

@description('Helm chart name')
param chartName string

@description('Helm chart version')
param chartVersion string

@description('Helm repo URL (e.g., https://charts.bitnami.com/bitnami)')
param chartRepo string

@description('Namespace to deploy the chart')
param namespace string = 'default'

@description('Helm values YAML')
@secure()
param chartValues string

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-03-01' = {
  name: releaseName
  scope: resourceGroup(aksResourceGroup)
  parent: resourceId('Microsoft.ContainerService/managedClusters', aksName)
  location: resourceGroup(aksResourceGroup).location
  properties: {
    extensionType: 'kubernetesconfiguration'
    autoUpgradeMinorVersion: true
    releaseNamespace: namespace
    configurationSettings: {
      'helm.chart': chartName
      'helm.repository': chartRepo
      'helm.version': chartVersion
    }
    configurationProtectedSettings: {
      'helm.values': chartValues
    }
  }
}
