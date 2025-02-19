
# Welcome to the Blue-Green-Deploy2 Project!

This repo contains artifacts for a blue/green deployment using both java-springboot and dotNet.


## blue-green-deploy2

## Notes:
- 22-Aug-2023: Initial Implementation.
* 25-Sept-2023: This setup has integrated [direnv](https://direnv.net). for managing environment variables & Azure Defaults.  Please see the .envrc file(s), customize to your needs.


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

Set defaults (if not using direnv.  Otherwise direnv will set it for you):
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

Set defaults (if not using direnv.  Otherwise direnv will set it for you):
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

Set Java to v17 locally [ent]:
```
sdk use java 17.0.11-librca
```

Set Java to v17 on app-instance:




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

Build Green App, Invoke maven build:
```
cd ../green
```

```
m3ast
```
or

```
mvn clean install
```

Blue App:

```
cd ../blue
```

Deploy Blue App from .jar:

```
az spring app deployment create -n spring-controller-blue -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --artifact-path ./target/hello-spring-controller-blue-0.0.5-SNAPSHOT.jar \
   --verbose &
```

Deploy Blue App from source:

```
az spring app deployment create -n spring-controller-blue -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --source-path . \
   --verbose &
```

Delete the DEFAULT deployment [yes you must do this!]: 
```
az spring app deployment delete --app app-cyan2 -n default \
   -s demo-blue-green-ent2 -g azure-asa-uswest \
   --verbose &
```

Green App:
```
cd ../green
```

Deploy Green App from .jar:

```
az spring app deployment create -n spring-controller-green -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --artifact-path ./target/hello-spring-controller-green-0.0.7-SNAPSHOT.jar \
   --verbose &
```

Deploy Green App from source:

```
az spring app deployment create -n spring-controller-green -s demo-blue-green-ent2 -g azure-asa-uswest \
   --app app-cyan2 \
   --source-path . \
   --verbose &
```

Make Active Blue App:
```
az spring app set-deployment -n app-cyan2 \
--deployment spring-controller-blue \
-s demo-blue-green-ent2 \
-g azure-asa-uswest \
--verbose &
```

Get logs [ Blue ]:
```
az spring app logs -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --lines 100 -f
```

Hit the app [default]:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/
```

Hit the app ReST Endpoint for logging:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```

```
curl -v https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/
```

```
curl -v https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```

Make Active Green App:
```
az spring app set-deployment -n app-cyan2 \
--deployment spring-controller-green \
-s demo-blue-green-ent2 \
-g azure-asa-uswest \
--verbose &
```

Get logs [ Green ]:
```
az spring app logs -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --lines 100 -f
```

Hit the app ReST Endpoint for logging:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```

List the app:
```
az spring app list  -s demo-blue-green-ent2 -g azure-asa-uswest
```

Hit the app [default]:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/
```

Hit the app ReST Endpoint for logging:
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
-19-Feb-2025:  -Corrected steps for Blue/Green Deploys.
               -Removed --build-env switch as this is no longer supported.
               -Updated grammer.

-25-Sep-2023:  -Updated to incorporate direnv.  Tailor the .envrc file to your own settings.
-05-Jan-2023:  -Initial Impl.






