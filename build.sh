#!/bin/bash
mvn package
mkdir -p target/META-INF/json
mvn exec:java -Dexec.mainClass="PrintSciJavaPluginJson"
mv org.scijava.plugin.Plugin target/META-INF/json/
(cd target; jar uf tr2d-assembly-0.1.0-SNAPSHOT.jar META-INF/json/org.scijava.plugin.Plugin)
rm target/META-INF/json/org.scijava.plugin.Plugin 
rmdir target/META-INF/json/
rmdir target/META-INF/
