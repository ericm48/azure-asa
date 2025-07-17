# Welcome to the Blue-Green-Deploy2 Quick Demo Page!


## Make Blue Active:
```
az spring app set-deployment -n app-cyan2 \
--deployment spring-controller-blue \
-s demo-blue-green-ent2 \
-g azure-asa-uswest \
--verbose &
```

## Get Blue Logs:
```
az spring app logs -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --lines 100 -f
```

## Hit The App:
```
https://demo-blue-green-ent2-app-cyan2.azuremicroservices.io/showDateTime
```

## Make Green Active:
```
az spring app set-deployment -n app-cyan2 \
--deployment spring-controller-green \
-s demo-blue-green-ent2 \
-g azure-asa-uswest \
--verbose &
```

## Get Green Logs:
```
az spring app logs -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest --lines 100 -f
```

## Delete The App:
```
az spring app delete -n app-cyan2 -s demo-blue-green-ent2 -g azure-asa-uswest &
```
