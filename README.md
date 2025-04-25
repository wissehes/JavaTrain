# JavaTrain

A server application that transforms the realtime data streams provided by the NDOV into a usable REST API and GraphQL API.

This project is inspired by the [GoTrain](https://github.com/rijdendetreinen/gotrain) project.

## How it works

The dynamic departure boards on the platforms at Dutch stations receive their data in the form of a message subscribe system.
There exists a public feed in the form of the [NDOV Realtime](https://data.ndovloket.nl/REALTIME.TXT) data.
The departure boards in particular use the InfoPlus Dynamische Vertrek Staat topic.

After subscribing to the `/RIG/InfoPlusDVSInterface4` topic, JavaTrain receives the InfoPlus messages in XML format.
It then converts those to Java classes and stores them in memory.
Thankfully, these messages are documented really well, see the [resources](./docs/Resources.md) file.

## Running

After pulling this repository, you can hit start in your IDE.
The swagger API docs are located at http://localhost:8080/swagger-ui/index.html.

