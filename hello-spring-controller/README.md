
# Welcome to the hello-spring-controller project!

Azure Spring Apps Enterprise Projects



## Setup Commands:

Login to Azure with cli.
```
az login
```

Verify correct account.
```
az account list
```

Verify correct subscription.
```
az account set --subscription ...
```

Create a group (run in background).
```
az group create --location westus --resource-group azure-asa-uswest &
```

Create a group (in background).
```
az group create --location westus --resource-group azure-asa-uswest &
```

Create app within the service-instance (in background)  Note: Takes 10-15min to complete.
```
az spring app create -n hello-spring-controller -s hello-spring-controller-ent \
	 -g azure-asa-uswest --assign-endpoint true --runtime-version=Java_11  \
	 --verbose &
```









## Demo Commands:

Login to Azure with cli.
```
az login
```

Verify correct account.
```
az account list
```

Verify correct subscription.
```
az account set --subscription ...
```




## Notes:





