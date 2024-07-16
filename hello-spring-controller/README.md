
# Welcome to the hello-spring-controller project!

Azure Spring Apps Enterprise Projects

Please note:
* Several commands are run in the background using the '&'.  You may wish to NOT do this.  Just be aware of what you are doing.
* Below are example commands for the ENTERPRISE Tier.  
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

Set defaults (if not using direnv.  Otherwise direnv will set for you):
```
. ../sh/setup-env-variables-jsb-ent.sh
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
cd ./hello-spring-controller
```

Set defaults (if not using direnv.  Otherwise direnv will set for you):
```
. ../sh/setup-env-variables-jsb-ent.sh
```

Create app within the service-instance [ent]. Note: Takes 10-15min to complete:
```
az spring app create -n hello-spring-controller -s hello-spring-controller-ent \
	 -g azure-asa-uswest --assign-endpoint true \
	 --verbose &
```

Verify the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
```

Set Java to v17 [ent]:
```
sdk use java 17.0.11-librca
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
az spring app deploy -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest \
   --artifact-path ./target/hello-spring-controller-0.0.8-RELEASE.jar \
   --build-env BP_JVM_VERSION=17 \
   --verbose  &	
```

List the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
```

Get logs [ent]:
```
az spring app logs -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest --lines 100 -f
```

Hit the app [ent]:

```
https://hello-spring-controller-ent-hello-spring-controller.azuremicroservices.io/showDateTime
```

```
curl -v https://hello-spring-controller-ent-hello-spring-controller.azuremicroservices.io/showDateTime
```

Scale the app [ent]:
```
az spring app scale -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest --instance-count 3
```

Delete the app [ent]:
```
az spring app delete -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest &
```


## Notes:
-25-Sep-2023:  Updated to incorporate direnv.  Tailor the .envrc file to your own settings.




