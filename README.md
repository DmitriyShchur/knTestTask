# Kuehne+Nagel test task

This is a simple "contact list" web application that allows:
- Listing people (name and photo)
- Searching by name
- Paging

An initial list is populated with attached people.csv

*addition/removal/edit wasn't implemented, because they are out of scope of this task*

#### You can use this application with one of following databases: 
* HSQLDB
* MySQL
* PostgreSQL
* Oracle

#### System prerequisites:
* Java 8 or higher
* Git

## Application properties

Application properties are located in the file
```
/config/application.properties
```
You can select the database by commenting and uncommenting the settings sections and setting your parameters.

*HSQLDB was chosen by default because it is enough for our requirements and can be easily launched directly from the project directory.*

If you want to import people from your own file, dont forget to change "photos.host.address" property. It used to separate non normalized csv file and to remove repeatable information from database.

## Running HSQLDB via Gradle

Run the following command in this repo to run a database server:

```bash
./gradlew startHsqldbServer
```

## Work with databases via Gradle

Run the following command in this repo to create tables:

```bash
./gradlew createTables
```

Run the following command in this repo to clean tables:

```bash
./gradlew cleanTables
```

Run the following command in this repo to drop tables:

```bash
./gradlew dropTables
```

Run the following command in this repo to fill a people table with people.csv data:

```bash
./gradlew fillFromPeopleFile
```

*people.csv should be placed in the project directory.*

## How to launch the application

1.Build the application
```bash
./gradlew clean build
```

2.Run the hsqldb server (or your own database).
*It must be run in a separate terminal*
```bash
./gradlew startHsqldbServer
```

3.Run the script that creates a table
```bash
./gradlew createTables
```

4.Run the script that fills a table
```bash
./gradlew fillFromPeopleFile
```

5.Run the application 
```bash
./gradlew bootRun
```
6.You can find launched application by the address http://localhost:8080