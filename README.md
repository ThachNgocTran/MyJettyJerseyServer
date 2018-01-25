# MyJettyJerseyServer
My attempt to create a web server (Jetty) with Restful support (Jersey). CORS support is also available! [2]

Compile: mvn package

Run: java -cp MyJettyJerseyServer-1.0.jar "com.mycompany.server.App"

The JAR is uber-jar. Java version: 1.8.0_151 x64. Compiled with Maven v3.5.0. Project file: Intellij IDEA 2017.3.

My favorite RESTful clients:

+ https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo

+ https://www.telerik.com/fiddler

Some testcases:

1. POST Request. Receive text back.

URL: http://localhost:8080/util/say/hello

Header: Content-type: application/json

POST with Data:
{
  "lastName": "John",
  "firstName": "Smith"
}

2. GET Request. Receive text back.

URL: http://localhost:8080/util/clock/date/next/22/07/2017

GET

3. GET Request. Receive JSON back.

http://localhost:8080/util/myUuid/get

GET

In addition, there is a naively simple usage of Google Guava Cache [1] for 1). When the data is not frequently changed, or the change is not needed to stay up-to-date ASAP, Cache is ideal. A real-life usecase is "Top most read newspaper articles" appearing on the side pane of online newspaper.

Reference:

[1] https://github.com/google/guava/wiki/CachesExplained

[2] https://en.wikipedia.org/wiki/Cross-origin_resource_sharing
