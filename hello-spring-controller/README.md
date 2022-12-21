
# Welcome to the hello-spring-controller project!

Azure Spring Apps Enterprise Projects



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
. ../sh/setup-env-variables.sh
```

Invoke maven build:
```
m3ast
```
or

```
mvn clean install
```






## Notes:





