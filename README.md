# Bon-Voyage - Backend

Main purpuse - searching monuments and information via external API's and saving planned trips.

The main goal of the app is to simplify the travel planning process.
This app will help users to search for attractions in the selected area, information about selected places, and to plan a route to these places. All details of the planned route will be saved in the database for further viewing.
Users don't need to open different applications or websites to find monuments and to plan an optimal route, all information will be available in one place immediately.

___

Java Spring Boot, PostgreSQL, MediaWiki API, HERE Discover API, HERE Browse API, Google Maps API

## Development

**Build server for docker**

Install dependencies: `mvn clean install`
Build server image: `docker build -t bon-voyage-server .`

**Docker**

```
docker-compose up -d            // Start containers
docker-compose down             // Stop containers

// After init.sql change:
docker-compose down --volumes   // Stop containers

docker image ls                 // List images and find 13.1-alpine
docker image rm {{IMAGE ID}}    // Remove image
```

PGWeb: [http://localhost:8081/]()