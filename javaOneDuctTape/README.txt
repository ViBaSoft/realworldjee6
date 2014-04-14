README:

Setup Required:
1) Adding Derby Modules
 - Create below directory in JBOSS_HOME 
 	modules\org\apache\derby\main
 - Add following files in this directory
 	-- derbyclient.jar
 	-- derby.jar (optional)
 	-- module.xml
 	
 	Content for module.xml ---
		 	<?xml version="1.0" encoding="UTF-8"?>
		<module xmlns="urn:jboss:module:1.0" name="org.apache.derby">
		        <resources>
		            <resource-root path="derbyclient.jar"/>
		        </resources>
		        <dependencies>
		            <module name="javax.api"/>
		        </dependencies>
		</module>
2) Add Datasource in standalone.xml in configuration directory as below
	<datasources>
			<datasource jndi-name="java:jboss/datasources/DerbyDS" pool-name="DerbyDS" enabled="true" use-ccm="false">
		                    <connection-url>jdbc:derby://localhost:1527/jhDB;create=true</connection-url>
		                    <driver>org.apache.derby</driver>
		                    <security>
		                        <user-name>demo</user-name>
		                        <password>demo</password>
		                    </security>
		                    <validation>
		                        <validate-on-match>false</validate-on-match>
		                        <background-validation>false</background-validation>
		                    </validation>
		                    <statement>
		                        <share-prepared-statements>false</share-prepared-statements>
		                    </statement>
		    </datasource>
		    <driver name="org.apache.derby" module="org.apache.derby">
		                        <xa-datasource-class>org.apache.derby.jdbc.ClientXADataSource</xa-datasource-class>
		    </driver>
    </datasources>
3) Install Arquillian using forge
	forge$> forge find-plugin test <-- this will find the plugins for unit testing, and list arquillian and other plugins available under test
	forge$> forge install-plugin arquillian <-- this will install arquillian plugin as listed in above command, also you can directly run the above command