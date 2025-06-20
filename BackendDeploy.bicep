// BackendDeploy.bicep
targetScope = 'subscription' // Keep only one declaration

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
  scope: resourceGroup(aksResourceGroup) // This is crucial for scoping the extension to the correct RG
  params: {
    aksName: aksName
    releaseName: releaseName
    chartName: chartName
    chartVersion: chartVersion
    chartRepo: chartRepo
    namespace: namespace
  }
}

// REMOVED: Duplicate targetScope = 'subscription' and related parameters.
// The parameters for BackendDeploy.bicep are already defined above.
// The provided snippet had them duplicated below the module definition.
// param aksName string // This was a duplicate
// param releaseName string // This was a duplicate
// param chartName string // This was a duplicate
// param chartVersion string // This was a duplicate
// param chartRepo string // This was a duplicate
// param namespace string // This was a duplicate