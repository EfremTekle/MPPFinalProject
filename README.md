# TwitterFunctionalProgramming

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally


```shell
mvn clean compile
mvn exec:java -Dexec.mainClass="com.company.ui.Main"
```

## Deploying the application with Docker

You need to install docker first, then simply run:

```shell
docker build -t social .
docker run -it social
```

## Copyright

Content is released under [CC-BY-4.0](https://creativecommons.org/licenses/by/4.0/).
