#### Generate Gradle Project
##### Use in Windows cmd or IDEA terminal (after that make sure that all dependencies are included in build.gradle)
```
mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create ^
-DprojectGroupId="com.tsystems" ^
-DprojectArtifactId="microprofile-fault-tolerance" ^
-DclassName="com.tsystems.faulttolerance.CoffeeResource" ^
-Dpath="/coffee" ^
-Dextensions="smallrye-fault-tolerance, resteasy-jsonb" ^
-DbuildTool="gradle"
```
##### Or run this one and configure your project in a terminal (even in PowerSell)
```
mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -Dextensions="resteasy-jsonb" -DbuildTool="gradle"
```

#### In IDEA
* build.gradle: right click, "Import Gradle Project"
* "Reimport Gradle Project" if required

#### Running and packaging
##### Developer mode
	mvnw compile quarkus:dev
	gradlew quarkusDev
##### Creating packages
	mvnw package
	gradlew build

#### Test
##### Unit tests
    mvnw test
    gradlew test
##### Endpoints with curl
###### GET
	curl http://127.0.0.1:8080/secured/permit-all
###### GET with debug
	curl -v http://127.0.0.1:8080/secured/permit-all
###### GET with formatting	
	curl -w "\n" http://localhost:8080/greeting
###### JWT Test:	
	curl -H "Authorization: Bearer YOUR_TOKEN" http://127.0.0.1:8080/secured/roles-allowed

#### Notes
##### Native
* If you use Response and Quarkus can’t determine the beans that are serialized, you need to annotate them with @RegisterForReflection
* While @Consumes and @Produces are optional as auto-negotiation is supported, it is heavily recommended to annotate your endpoints with them to define precisely the expected content-types
* 
##### General
* No-args constructor required for JSON serialization
* Quarkus uses a default scoping of @ApplicationScoped
* Panache Entity: And thanks to our field access rewrite, when your users read person.name they will actually call your getName() accessor, and similarly for field writes and the setter. This allows for proper encapsulation at runtime as all fields calls will be replaced by the corresponding getter/setter calls.
* The stream methods require a transaction to work
* 