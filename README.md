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
http://localhost:8080/util/uuid/get
GET

