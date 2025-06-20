// BackendHelm.bicep
param aksName string // RE-ADD THIS LINE - It's essential for referencing the AKS cluster
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string

// We need to define the AKS cluster as an existing resource
// because the extension needs to be scoped to it.
resource aks 'Microsoft.ContainerService/managedClusters@2023-01-01' existing = {
  name: aksName // This now correctly references the 'aksName' parameter of this module
}

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-11-01' = {
  name: releaseName
  // The 'scope' property for this resource type, when defined at the top level,
  // should point to the parent resource, which is the AKS cluster.
  scope: aks // This correctly references the existing 'aks' resource defined above
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