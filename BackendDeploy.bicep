param releaseName string
param namespace string
param chartName string
param chartVersion string
param acrName string

resource helmChart 'Microsoft.KubernetesConfiguration/helmCharts@2022-11-01' = {
  name: releaseName
  properties: {
    chartName: chartName
    chartVersion: chartVersion
    repositoryUrl: 'oci://${acrName}.azurecr.io/helm'
    scope: {
      namespace: namespace
      releaseNamespace: namespace
    }
  }
}
