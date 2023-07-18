
# Welcome to the Blue-Green-Deploy Project!

This repo contains artifacts for a blue/green deployment using both java-springboot and dotNet.


## blue-green-deploy

## Notes:
- 18-Jul-2023: Initial Implementation.

## Setup Commands:

Login to Azure with cli:
```
az login
```

Verify correct account:
```
az account list
```

Verify correct subscription:
```
az account set --subscription ...
```

Set defaults:
```
. ../sh/setup-env-variables-bg-ent.sh
```


Create the group:
```
az group create --location westus --resource-group azure-asa-uswest &
```

Verify the group:
```
az group list
```


## Demo Commands:

Login to Azure with cli:
```
az login
```

Goto proper directory:
```
cd ./blue-green-deplo
```

Set defaults:
```
. ../sh/setup-env-variables-bg-ent.sh
```

Create app within the service-instance [ent]. Note: Takes 10-15min to complete:
```
az spring app create -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest \
   --assign-endpoint true \
   --verbose &
```

Verify the app [ent]:
```
az spring app list  -s demo-blue-green-ent -g azure-asa-uswest
```

Invoke maven build:
```
m3ast
```
or

```
mvn clean install
```

Deploy the app [ent]:
```
az spring app deploy -n hello-spring-controller -s demo-blue-green-ent -g azure-asa-uswest \
   --artifact-path ./target/hello-spring-controller-0.0.1-SNAPSHOT.jar \
   --build-env BP_JVM_VERSION=17 \
   --verbose  & 
```

List the app [ent]:
```
az spring app list  -s demo-blue-green-ent -g azure-asa-uswest
```

Get logs [ent]:
```
az spring app logs -n hello-spring-controller -s demo-blue-green-ent -g azure-asa-uswest --lines 100 -f
```

Hit the app [ent]:

```
https://demo-blue-green-ent-hello-spring-controller.azuremicroservices.io/showDateTime
```

```
curl -v https://demo-blue-green-ent-hello-spring-controller.azuremicroservices.io/showDateTime
```

Scale the app [ent]:
```
az spring app scale -n hello-spring-controller -s demo-blue-green-ent -g azure-asa-uswest --instance-count 3
```

Delete the app [ent]:
```
az spring app delete -n hello-spring-controller -s demo-blue-green-ent -g azure-asa-uswest &
```


## Notes:






