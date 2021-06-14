helm repo add bitnami https://charts.bitnami.com/bitnami
helm install pg -f ./postgres_values.yaml bitnami/postgresql