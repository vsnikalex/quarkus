#### Generate Gradle Project
```
mvn io.quarkus:quarkus-maven-plugin:1.5.0.Final:create -Dextensions="resteasy-jsonb" -DbuildTool="gradle"
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
##### Native compilation (given Visual Studio 2017 Visual C++ Build Tools installed, from PowerShell)
    cmd /c 'call "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars64.bat" && mvn package -Pnative'

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
###### For JWT:	
	curl -H "Authorization: Bearer YOUR_TOKEN" http://127.0.0.1:8080/secured/roles-allowed
###### For SSE:	
    curl -N http://localhost:8080/hello/stream/5/neo

#### Notes
##### Native
* If you use Response and Quarkus can’t determine the beans that are serialized, you need to annotate them with @RegisterForReflection
* For the third-party JARs registering for reflection, the usage of reflection-config.json is required
* While @Consumes and @Produces are optional as auto-negotiation is supported, it is heavily recommended to annotate your endpoints with them to define precisely the expected content-types
* Quarkus automatically includes the resources present in META-INF/resources (the web resources) but, outside of this directory, you are on your own. To include more resources in the native executable, create a resources-config.json JSON file defining which resources should be included
* 
##### General
* No-args constructor required for JSON serialization
* Quarkus uses a default scoping of @ApplicationScoped
* Panache Entity: And thanks to our field access rewrite, when your users read person.name they will actually call your getName() accessor, and similarly for field writes and the setter. This allows for proper encapsulation at runtime as all fields calls will be replaced by the corresponding getter/setter calls.
* The stream methods require a transaction to work
* If you don’t want to bother defining getters/setters for your entities, you can make them extend PanacheEntityBase and Quarkus will generate them for you. You can even extend PanacheEntity and take advantage of the default ID it provides.
* You should only use list and stream methods if your table contains small enough data sets. For larger data sets you can use the find method equivalents, which return a PanacheQuery on which you can do paging
* Use @JsonbTransient to avoid infinite loops when serializing with JSON-B
* Infispan: If your classes have only mutable fields, then the @ProtoFactory is not required, assuming your class has a no arg constructor.
* Messaging: If you don’t want to create a deserializer for each of your pojo, you can use the generic io.vertx.kafka.client.serialization.JsonObjectDeserializer that will deserialize to a javax.json.JsonObject. The corresponding serializer can also be used: io.vertx.kafka.client.serialization.JsonObjectSerializer.
* 