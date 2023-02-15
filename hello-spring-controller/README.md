
# Welcome to the hello-spring-controller project!

Azure Spring Apps Enterprise Projects

Please note:
* Several commands are run in the background using the '&'.  You may wish to NOT do this.  Just be aware of what you are doing.
* Below are example commands for both tiers BASIC & ENTERPRISE.  There are subtle differences.


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
. ../sh/setup-env-variables-jsb-basic.sh
```

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

Create app within the service-instance [basic]. Note: Takes 10-15min to complete:
```
az spring app create -n hello-spring-controller -s hello-spring-controller-basic \
	 -g azure-asa-uswest --assign-endpoint true --runtime-version=Java_11  \
	 --verbose &
```

Create app within the service-instance [ent]. Note: Takes 10-15min to complete:
```
az spring app create -n hello-spring-controller -s hello-spring-controller-ent \
	 -g azure-asa-uswest --assign-endpoint true \
	 --verbose &
```

Verify the app [basic]:
```
az spring app list  -s hello-spring-controller-basic -g azure-asa-uswest
```

Verify the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
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

Set defaults:
```
. ../sh/setup-env-variables-jsb-basic.sh
```

```
. ../sh/setup-env-variables-jsb-ent.sh
```

From Folder:
```
./hello-spring-controller
```

Invoke maven build:
```
m3ast
```
or

```
mvn clean install
```

Deploy the app [basic]:
```
az spring app deploy -n hello-spring-controller -s hello-spring-controller-basic \
   -g azure-asa-uswest --artifact-path ./target/hello-spring-controller-0.0.1-SNAPSHOT.jar \
   --verbose  &
```

Deploy the app [ent]:
```
az spring app deploy -n hello-spring-controller -s hello-spring-controller-ent \
   -g azure-asa-uswest --artifact-path ./target/hello-spring-controller-0.0.1-SNAPSHOT.jar \
   --verbose  &
```


List the app [basic]:
```
az spring app list  -s hello-spring-controller-basic -g azure-asa-uswest
```

List the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
```


Get logs [basic]:
```
az spring app logs -n hello-spring-controller -s hello-spring-controller-basic -g azure-asa-uswest --lines 100 -f
```

Get logs [ent]:
```
az spring app logs -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest --lines 100 -f
```

Hit the app [basic]:

```
https://hello-spring-controller-basic-hello-spring-controller.azuremicroservices.io/
```

```
curl -v https://hello-spring-controller-basic-hello-spring-controller.azuremicroservices.io/
```


Hit the app [ent]:

```
https://hello-spring-controller-ent-hello-spring-controller.azuremicroservices.io/
```

```
curl -v https://hello-spring-controller-ent-hello-spring-controller.azuremicroservices.io/
```


Delete the app [basic]:
```
az spring app delete -n hello-spring-controller -s hello-spring-controller-basic -g azure-asa-uswest &
```

Delete the app [ent]:
```
az spring app delete -n hello-spring-controller -s hello-spring-controller-ent -g azure-asa-uswest &
```


## Notes:





