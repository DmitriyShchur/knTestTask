# Kuehne+Nagel test task

Prerequisites:
* Java 8 or higher
* Git

## Running HSQLDB via Gradle

Run the following command in this repo to run database server:

```bash
./gradlew startHsqldbServer
```

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

Run the following command in this repo to fill people table with people.csv data:

```bash
./gradlew fillFromPeopleFile
```

