# -cicd-pipelines-gitops

oc create sa pipeline-bot

oc adm policy add-scc-to-user anyuid -z pipeline-bot


oc create secret docker-registry quay-auth-secret \
    --docker-server=quay-quay-image-registry.apps.cluster-vg29h.vg29h.sandbox1619.opentlc.com \
    --docker-username="imesquit+locations" \
    --docker-password=<quay-robot-secret> \
    --namespace=location-pipeline

openssl rand -base64 32

oc create secret generic github-webhook-secret \
   --from-literal=secretToken=<genetated-secret> \
   --namespace=location-pipeline
