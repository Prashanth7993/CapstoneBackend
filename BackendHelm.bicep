// BackendHelm.bicep
// param aksName string // REMOVE this line - it's no longer used directly in this file
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string

// We need to define the AKS cluster as an existing resource
// because the extension needs to be scoped to it.
resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName // This 'aksName' will come from the module parameter
}

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-11-01' = {
  name: releaseName
  // The 'scope' property for this resource type, when defined at the top level,
  // should point to the parent resource, which is the AKS cluster.
  scope: aks // CORRECT: Referencing the existing 'aks' resource
  properties: {
    extensionType: 'kubernetesConfiguration'
    autoUpgradeMinorVersion: true
    releaseTrain: 'Stable'
    version: chartVersion
    // This 'scope' property within 'properties' defines the scope of the Helm release
    // *within the Kubernetes cluster* (e.g., cluster-wide or namespace-specific).
    scope: {
      cluster: {
        releaseNamespace: namespace
      }
    }
    configurationSettings: {
      chartName: chartName
      repositoryUrl: chartRepo
    }
  }
}