
# Welcome to the Blue-Green-Deploy2 Project!

This repo contains artifacts for a blue/green deployment using both java-springboot and dotNet.


## blue-green-deploy2

## Notes:
- 22-Aug-2023: Initial Implementation.

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
source ../sh/setup-env-variables-bg-ent2.sh
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
cd ./blue-green-deploy2
```

Set defaults:
```
source ../sh/setup-env-variables-bg-ent2.sh
```

Create app within the service-instance. Note: Takes 10-15min to complete:
```
az spring app create -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest \
   --assign-endpoint true \
   --verbose &
```

Verify the app:
```
az spring app list  -s demo-blue-green-ent2 -g azure-asa-uswest -o table
```

Delete the DEFAULT deployment [yes you must do this!]: 
```
az spring app deployment delete --app app-cyan2 -n default \
   -s demo-blue-green-ent2 -g azure-asa-uswest \
   --verbose &
```

Build Green App, Invoke maven build:
```
cd green
```

```
m3ast
```
or

```
mvn clean install
```

```
cd ..
```

Build Blue App, Invoke maven build:
```
cd blue
```
```
m3ast
```
or

```
mvn clean install
```

Deploy Green App:
```
cd ../green
```

```
az spring app deployment create -n spring-controller-green -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --artifact-path ./target/hello-spring-controller-green-0.0.7-SNAPSHOT.jar \
   --build-env BP_JVM_VERSION=17 \
   --verbose &
```

Deploy Blue App:
```
cd ../blue
```

```
az spring app deployment create -n spring-controller-blue -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --artifact-path ./target/hello-spring-controller-blue-0.0.5-SNAPSHOT.jar \
   --build-env BP_JVM_VERSION=17 \
   --verbose &
```

Make Active Green App:
```
az spring app set-deployment --deployment spring-controller-green -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \  
   --verbose &
```

Make Active Blue App:
```
az spring app set-deployment --deployment spring-controller-blue -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \  
   --verbose &
```

Get logs:
```
az spring app logs -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --lines 100 -f
```


List the app:
```
az spring app list  -s demo-blue-green-ent2 -g azure-asa-uswest
```

Hit the app [default]:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/
```

Hit the app ReST Endpoint on Green for logging:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```


```
curl -v https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/
```

```
curl -v https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```


Scale UP the app:
```
az spring app scale -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --instance-count 3
```

Scale DOWN the app:
```
az spring app scale -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --instance-count 1
```

Delete the app [ent]:
```
az spring app delete -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest &
```


## Notes:






