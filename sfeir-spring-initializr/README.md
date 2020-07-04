
## sfeir-spring-initializr

Companion project for my talk about how to tailor spring initiliazr to generate custom spring boot projects.

* [Slides](https://docs.google.com/presentation/d/1NLSd06xBRqMy4eYNAQXUaqzkeejy8gwZNvJrYgU5Nig)  

### Use case of this custom Spring Initializr

* Sensible default project metadata (groupId, artifactId, etc)
* **Packaging**: Jar only
* **Languages**: Java Only(11 default, 14) 
* **Build Systems**: Maven only
* All dependencies available on [start.spring.io](https://start.spring.io) + our homemade testing library: [Spring EsData Loader](https://github.com/tinesoft/spring-esdata-loader)
* **Auto-add our library** if Spring Data Elasticsearch is requested 
* **Auto-create the “src/test/resources/esdata” folder** is our library is requested

### Some Technical information about this custom Spring Initializr

* This customizer is based on **v0.9.0-SNAPSHOT** of the **Initializr Library**, because current released version (v0.8.0.RELEASE) will make the application fail on start due to of this change in [Spring projects versioning](https://spring.io/blog/2020/04/30/updates-to-spring-versions). The [fix](https://github.com/spring-io/initializr/issues/1083) is ready but not yet released 
* The application entrypoint ([SfeirSpringInitializrApplication](https://github.com/tinesoft/tutorials/tree/master/sfeir-spring-initializr/src/main/java/com/sfeir/spring/initializr/app)) has been moved into its own subpackage (`.app`), otherwise application start will fail due to Spring Boot trying to init beans defined in our custom [ProjectGenerationConfiguration](https://github.com/tinesoft/tutorials/blob/master/sfeir-spring-initializr/src/main/java/com/sfeir/spring/initializr/configuration/SpringEsDataLoaderProjectGenerationConfiguration.java)(see this [thread](https://gitter.im/spring-io/initializr?at=5d8ca83e290b8c354adab880) on Gitter, for more information)


