
# Welcome to the hello-steel-controller-basic project!

Azure Spring Apps Enterprise Projects

Azure Spring Apps Enterprise Projects

Please note:
* Several commands are run in the background using the '&'.  You may wish to NOT do this.  Just be aware of what you are doing.
* Below are example commands for the BASIC tier ONLY. ENTERPRISE will be added at a later date.
* Using dotnet-cli 3.1.425


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
. ../sh/setup-env-variables-cst-basic.sh
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
az spring-cloud app create -n hello-steel -s hello-steel-controller-basic -g azure-asa-uswest --is-public &
```


Verify the app [basic]:
```
az spring app list  -s hello-steel-controller-basic -g azure-asa-uswest
```


## Demo Commands:

Login to Azure with cli:
```
az login
```

Set defaults:
```
. ./sh/setup-env-variables-cst-basic.sh
```

Goto proper directory:
```
cd ./hello-steel-controller-basic
```

Clear Environment Variable: VERSION
***Please Note:  If you happen to have an environment variable named VERSION set for some other app, there is a good chance the value here is not legit for the dotnet-cli, and will cause it to fail or produce unexpected results.
```
unset VERSION
```

Invoke dotnet build:
```
dotnet build
```

Package the app:
```
dotnet publish -c release -o ./publish
```


Deploy the app [basic]:
```
az spring-cloud app deploy -n hello-steel -s hello-steel-controller-basic \
   -g azure-asa-uswest --runtime-version NetCore_31 --main-entry hello-steel-controller-basic.dll \
   --artifact-path ./deploy.zip &
```

List the app [basic]:
```
az spring-cloud app list -s hello-steel-controller-basic -g azure-asa-uswest
```


Get logs [basic]:
```
az spring app logs -n hello-steel -s hello-steel-controller-basic -g azure-asa-uswest --lines 100 -f
```


Hit the app [basic]:

```
https://hello-spring-controller-basic-hello-spring-controller.azuremicroservices.io/weatherforecast
```

```
curl -v https://hello-steel-controller-basic-hello-steel.azuremicroservices.io/weatherforecast
```


Delete the app [basic]:
```
az spring app delete -n hello-steel -s hello-steel-controller-basic -g azure-asa-uswest &
```


## Notes:





