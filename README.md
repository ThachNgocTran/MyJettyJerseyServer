# MyJettyJerseyServer
My attempt to create a HTTP server (Jetty) with Restful support (Jersey). Thanks to the Internet. :)

Compile: mvn package

Run: java -jar MyJettyJerseyServer-1.0.jar

The JAR is uber-jar. Java version tested: 1.8.0_131 x64.

My favorite RESTful client: https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo

Some testcases:

1. POST Request. Receive text back.

URL: http://localhost:8080/util/say/hello

Header: Content-type: application/json

POST Data:
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
