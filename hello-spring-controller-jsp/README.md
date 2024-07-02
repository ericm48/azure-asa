
# Welcome to the hello-spring-controller-jsp project!

Azure Spring Apps Enterprise Projects

Please note:
* This demo uses a specific maven settings.xml, referred to below.  Make sure all mvn commands reference this setting.xml.
* This demo will deploy a spring boot jsp app with a special dependency (from spring-commerical) repo. 
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
cd ./hello-spring-controller-jsp
```

Set defaults (if not using direnv.  Otherwise direnv will set for you):
```
. ../sh/setup-env-variables-jsb-ent.sh
```

Create app-slot within the service-instance [ent]. Note: Takes 10-15min to complete:
```
az spring app create -n hello-spring-controller-jsp -s hello-spring-controller-ent \
	 -g azure-asa-uswest --assign-endpoint true \
	 --verbose &
```

Verify the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
```

Invoke maven build:
```
m3ast --settings ./mvn/settings.xml 
```
or

```
mvn clean install  --settings ./mvn/settings.xml 
```


Show micrometer dependency [ currently version: 1.10.15 ]:
```
m3dl --settings ./mvn/settings.xml |grep micro
```
or
```
mvn dependency:list  --settings ./mvn/settings.xml |grep micro
```

Shows the dependency fullfilled by spring-commerical repo (ie not maven central):
```
io.micrometer:micrometer-jetty11:pom:1.10.15:compile
```

To complete a maven release [ deploy ]:
```
m3 clean deploy --settings ./mvn/settings.xml  -Dmaven.test.skip=true
```
or
```
mvn clean deploy --settings ./mvn/settings.xml  -Dmaven.test.skip=true
```


Deploy the app to ASAE [ent]:
```
az spring app deploy -n hello-spring-controller-jsp -s hello-spring-controller-ent -g azure-asa-uswest \
   --artifact-path ./target/hello-spring-controller-jsp-0.0.8-RELEASE.war \
   --build-env BP_JVM_VERSION=17 \
   --verbose  &	
```

List the app [ent]:
```
az spring app list  -s hello-spring-controller-ent -g azure-asa-uswest
```

Get logs [ent]:
```
az spring app logs -n hello-spring-controller-jsp -s hello-spring-controller-ent -g azure-asa-uswest --lines 100 -f
```

Hit the app [ent]:

```
https://hello-spring-controller-ent-hello-spring-controller-jsp.azuremicroservices.io
```

```
curl -v https://hello-spring-controller-ent-hello-spring-controller-jsp.azuremicroservices.io
```

Scale the app [ent]:
```
az spring app scale -n hello-spring-controller-jsp -s hello-spring-controller-ent -g azure-asa-uswest --instance-count 3
```

Delete the app [ent]:
```
az spring app delete -n hello-spring-controller-jsp -s hello-spring-controller-ent -g azure-asa-uswest &
```


## Notes:
-02-Jul-2024: -Updated for Nexus Proxy for spring-commercial.
-23-May-2024: -Initial Implementation.




