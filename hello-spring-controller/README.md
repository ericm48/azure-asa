
# Welcome to the hello-spring-controller project!

Azure Spring Apps Enterprise Projects

Please note:
-Several commands are run in the background using the '&'.  You may wish to NOT do this.
Just be aware of what you are doing.

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
. ../sh/setup-env-variables-basic.sh
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

Verify the app:
```
az spring app list  -s hello-spring-controller-basic -g azure-asa-uswest
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
. ../sh/setup-env-variables-basic.sh
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

Get logs [basic]:
```
az spring app logs -n hello-spring-controller -s hello-spring-controller-basic -g azure-asa-uswest --lines 100 -f
```

Hit the app [basic]:

```
https://hello-spring-controller-basic-hello-spring-controller.azuremicroservices.io/
```

```
curl -v https://hello-spring-controller-basic-hello-spring-controller.azuremicroservices.io/
```

Delete the app [basic]:
```
az spring app delete -n hello-spring-controller -s hello-spring-controller-basic -g azure-asa-uswest &
```



## Notes:





