#!/bin/bash
#
#  Pull down certs for mvn from various Artifactories
#

#set -x

usage(){
   			echo " "
   			echo " "
   			echo " "
   			echo "Usage:   $0"
   			echo " "
   			echo " "
   			echo " "
	exit 1
}

	# First make sure JAVA_HOME is set...
	if [ -z "$JAVA_HOME" ]

	then
	      echo "\$JAVA_HOME is empty!!!"
	      #export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk1.8.0_221.jdk/Contents/Home"
	      export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.11.jdk/Contents/Home"
	      echo " "
	      echo "Setting JAVA_HOME=$JAVA_HOME"
	      echo " "
	else
	      echo "\$JAVA_HOME is NOT empty"
	fi
    
  cd ${JAVA_HOME}/lib/security  




  # MVN Central
	targetEndpoint='repo.maven.apache.org:443'

	echo ""
	echo "Processing: $targetEndpoint"

	echo "" | openssl s_client -connect $targetEndpoint > centralcerts
	keytool -delete -storepass changeit -alias central -keystore cacerts
	keytool -importcert -storepass changeit -noprompt -trustcacerts -file centralcerts -keystore cacerts -alias central


	# My Nexus: nexus-releases & spring-commerical
	# http://nexus.azure.csp-si-tiger.net/repository/spring-commercial-nexus/
	
	targetEndpoint='nexus.azure.csp-si-tiger.net:443'

	echo ""
	echo "Processing: $targetEndpoint"

	echo "" | openssl s_client -connect $targetEndpoint > nexusazurecerts
	keytool -delete -storepass changeit -alias nexusazure -keystore cacerts
	keytool -importcert -storepass changeit -noprompt -trustcacerts -file nexusazurecerts -keystore cacerts -alias nexusazure


