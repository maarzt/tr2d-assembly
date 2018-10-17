# tr2d-assembly

This project makes it very easy to deploy and start Tr2d. Tr2d will be packed into a self containing uber jar.

Just three steps:
1. Run build.sh, this creates the uber jar: target/tr2d-assembly-0.1.0-SNAPSHOT.jar
2. (In Linux:) Set the executable bit for the uber jar.
3. Double click on the uber jar to start Tr2d.

## How does it work:

"build.sh" does the following things:
1. It runs: ```mvn package```. This will build the uber jar, using the maven-shade-plugin.
2. It runs the java class ```PrintSciJavaPluginJson``` using maven.
   Maven thereby provides a class path, that contains all classes necessary to run ImageJ and Tr2d. 
   The class ```PrintSciJavaPlugJson``` enumerates all the classes in the class path, that are annotated with org.scijava.plugin.Plugin and writes their names into the file:
   "org.scijava.plugin.Plugin".
3. The file "META-INF/json/org.scijava.plugin.Plugin" is added to the uber jar.

With the file "META-INF/json/org.scijava.plugin.Plugin" in place, the uber jar can run independently of maven.
