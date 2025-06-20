// BackendHelm.bicep
param aksName string
param releaseName string
param chartName string
param chartVersion string
param chartRepo string
param namespace string

// REMOVED: The 'aks' existing resource declaration is not needed here
// as the extension deployment doesn't directly use it as a parent/scope.
// The association is implied by the module's deployment scope.

resource helmExtension 'Microsoft.KubernetesConfiguration/extensions@2022-11-01' = {
  name: releaseName
  // REMOVED: The 'scope' property here (at the top-level resource) is incorrect.
  // The deployment scope is handled by the module call in BackendDeploy.bicep.
  // The 'scope' *within* properties is for the Kubernetes-internal scope.
  properties: {
    extensionType: 'kubernetesConfiguration'
    autoUpgradeMinorVersion: true
    releaseTrain: 'Stable'
    version: chartVersion
    // This 'scope' property correctly defines the scope within the Kubernetes cluster itself.
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