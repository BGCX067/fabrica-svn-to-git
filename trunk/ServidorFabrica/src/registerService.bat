@echo on
set axis_home=d:\axis\axis-1_3

set NAME=RecibirSolicitudFabricacion
set PACKAGE=solicitudfabricacion\webservices\commons\zara

set cp=%axis_home%\lib\axis.jar;%axis_home%\lib\axis-ant.jar;%axis_home%\lib\commons-discovery-0.2.jar;%axis_home%\lib\commons-logging-1.0.4.jar;%axis_home%\lib\jaxrpc.jar;%axis_home%\lib\log4j-1.2.8.jar;%axis_home%\lib\saaj.jar;%axis_home%\lib\wsdl4j-1.5.1.jar
set cp=%cp%;.\bin;%CLASSPATH%;

java -classpath "%cp%" org.apache.axis.client.AdminClient deploy.wsdd
