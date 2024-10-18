# Helidon MP MVC JStachio

An example of a custom [Jersey MVC](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/mvc.html)
 implementation that integrates with [JStachio](https://github.com/jstachio/jstachio).

## Build and run

With JDK21
```shell
mvn package
java -jar target/helidon-mvc-jstachio.jar
```

## Exercise the application

```shell
curl -X GET http://localhost:8080
```
