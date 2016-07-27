# Script Manager

This application is used to process all the star wars script and show the processed information on the web

### Requirements

* Java 8
* Maven

**Run Tests**

```bash
$ mvn test
```

**Run Application**

```bash
$ mvn spring-boot:run
```


### REST API


#### POST /scripts

Receives the movie script in *plain/text* format and process all data


#### GET /settings

Returns all information about all settings


#### GET/settings/{id}

Returns all information about the setting with the given id


#### GET /characters

Returns all information about all characters


#### GET /characters/{id}

Returns all information about the character with the given id


