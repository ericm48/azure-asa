
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
cd ./blue-green-deploy
```

Set defaults:
```
. ../sh/setup-env-variables-bg-ent.sh
```

Create app within the service-instance. Note: Takes 10-15min to complete:
```
az spring app create -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest \
   --assign-endpoint true \
   --verbose &
```

Verify the app:
```
az spring app list  -s demo-blue-green-ent -g azure-asa-uswest
```

Delete the DEFAULT deployment [yes you must do this!]: 
```
az spring app deployment delete --app app-cyan -n default \
   -s demo-blue-green-ent -g azure-asa-uswest \
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

Build Blue App, Invoke donet build:
```
cd blue
```

```
dotnet build
```

```
cd ..
```

Deploy Green App:
```
az spring app deploy -n green-spring-controller -s demo-blue-green-ent -g azure-asa-uswest \
   --artifact-path ./target/hello-spring-controller-0.0.1-SNAPSHOT.jar \
   --build-env BP_JVM_VERSION=17 \
   --verbose  & 
```

Deploy Blue App:
```
az spring app deployment create -n blue-dotnet-controller -s demo-blue-green-ent -g azure-asa-uswest \
   --app app-cyan \
   --source-path ./blue \
   --verbose &
```

Make Active Green App:
```
az spring app set-deployment --deployment green-spring-controller \
   -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest \
   --verbose &
```

Make Active Blue App:
```
az spring app set-deployment --deployment blue-dotnet-controller \
   -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest \
   --verbose &
```

Get logs:
```
az spring app logs -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest --lines 100 -f
```
Note: There is a BIG difference with these between dotNet & Java.


List the app:
```
az spring app list  -s demo-blue-green-ent -g azure-asa-uswest
```

Hit the app [default]:
```
https://demo-blue-green-ent-app-cyan.azuremicroservices.io/
```

Hit the app ReST Endpoint on Green for logging:
```
https://demo-blue-green-ent-app-cyan.azuremicroservices.io/showDateTime
```


```
curl -v https://demo-blue-green-ent-app-cyan.azuremicroservices.io/
```

```
curl -v https://demo-blue-green-ent-app-cyan.azuremicroservices.io/showDateTime
```


Scale UP the app:
```
az spring app scale -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest --instance-count 3
```

Scale DOWN the app:
```
az spring app scale -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest --instance-count 1
```

Delete the app [ent]:
```
az spring app delete -n app-cyan -s demo-blue-green-ent -g azure-asa-uswest &
```


## Notes:






