#!/bin/bash

echo "Removing the current setup .... "
rm -rf /opt/tomcat/webapps/timeSheet
rm -f /opt/tomcat/webapps/timeSheet.war
# This remove's Tomcat's cache, which we don't want to have as it does cause problems.
rm -rf /opt/tomcat/work/Catalina/localhost/PaySystem
echo "Done removing the current deployment"

mvn clean install

echo "Deploying the new war file"
cp target/*.war /opt/tomcat/webapps
echo "Deployed the war file.  We are a go!"