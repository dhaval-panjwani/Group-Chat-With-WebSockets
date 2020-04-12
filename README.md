# Spring-based WebSocket Chat Server
The package includes a Web client which can be used to exercise the
functionality.

Build the software as follows:

    mvn clean package

Run it as follows, changing the port to 9090:

    java -Dserver.port=8080 -jar target/chat-server-0.1.0.jar

Connect to the web client at: http:://<hostname>:8080/.
