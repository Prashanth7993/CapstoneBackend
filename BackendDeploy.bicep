// BackendDeploy.bicep
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

@description('Helm chart repo URL')
param chartRepo string

@description('Namespace to deploy')
param namespace string

module helmDeploy 'BackendHelm.bicep' = {
  name: 'helmDeployModule'
  // The scope of the module itself is the resource group containing the AKS cluster.
  // This is correct as Bicep deployments at subscription scope operate on resource groups.
  scope: resourceGroup(aksResourceGroup)
  params: {
    aksName: aksName // Pass aksName to the module
    releaseName: releaseName
    chartName: chartName
    chartVersion: chartVersion
    chartRepo: chartRepo
    namespace: namespace
  }
}