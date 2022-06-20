#!/bin/bash
JAVA_TOOL_OPTIONS="-Dhttps.protocols=TLSv1.2 -Djavax.net.ssl.trustStore=cacerts -Djavax.net.ssl.trustStorePassword=changeit -Djavax.net.ssl.trustStoreType=JKS"
./mvnw -s sh/settings-private.xml clean install -DskipTests=true
