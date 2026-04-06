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

# github
oc annotate secret github-credential \
    "tekton.dev/git-0=https://github.com" -n app-pipeline-dev

oc secrets link pipeline github-creds -n app-pipeline-dev

# Vincula a secret para autenticação (pull) e montagem no pod (mount)
oc secrets link pipeline quay-auth-secret --for=pull,mount -n app-pipeline-dev

# Se você estiver usando a SA 'pipeline-bot' no Trigger, vincule a ela também por segurança
oc secrets link pipeline-bot quay-auth-secret --for=pull,mount -n app-pipeline-dev



# 1. Dá permissão de admin para o ArgoCD gerenciar o namespace da aplicação
oc adm policy add-role-to-user admin system:serviceaccount:openshift-gitops:openshift-gitops-argocd-application-controller -n location-app

# 2. (Opcional, mas recomendado) Garante que o namespace está marcado como gerenciado
oc label namespace location-app argocd.argoproj.io/managed-by=openshift-gitops --overwrite


oc get secret quay-auth-secret -n location-pipeline -o yaml | \
sed 's/namespace: location-pipeline/namespace: location-app/' | \
oc apply -f -

oc secrets link default quay-auth-secret --for=pull -n location-app

erro resolvers
https://access.redhat.com/solutions/7054083