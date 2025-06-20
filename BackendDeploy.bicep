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
  scope: resourceGroup(aksResourceGroup)
  params: {
    aksName: aksName // Ensure this is correctly passing the param from BackendDeploy to BackendHelm
    releaseName: releaseName
    chartName: chartName
    chartVersion: chartVersion
    chartRepo: chartRepo
    namespace: namespace
  }
}

// *** IMPORTANT: Ensure there are NO other 'param' declarations or 'params' blocks below this point in BackendDeploy.bicep.
// Remove anything like:
/*
param aksName string // REMOVE THIS IF IT'S DUPLICATED HERE
param releaseName string // REMOVE THIS IF IT'S DUPLICATED HERE
// ... and so on for other params if they are duplicated
*/
// Also remove any block that starts with `params: { ... }` that is not part of the module call.