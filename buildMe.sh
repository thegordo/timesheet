#!/bin/bash

echo "Removing the current setup .... "
rm -rf /opt/tomcat/webapps/timeSheet
rm -f /opt/tomcat/webapps/timeSheet.war
echo "Done removing the current deployment"

mvn

echo "Deploying the new war file"
cp target/timeSheet.war /opt/tomcat/webapps
echo "Deployed the war file.  We are a go!"