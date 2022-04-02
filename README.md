### API REST PERSONAL CV

#### Running Unit Tests With Maven

You can run unit tests by using the following command:
> `mvn clean test`

To run unit tests during build and package the application is used `maven-surefire-plugin`
The Surefire Plugin is used during the test phase of the build lifecycle to execute the unit tests of an application.

#### The Spring Boot Maven Plugin provides Spring Boot support in Apache Maven. It allows you to package executable jar or war archives,run Spring Boot applications, generate build information and start your Spring Boot application prior to running integration tests.

    <build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

Before we can start PostgreSQL with Docker Compose, we need to turn our Spring Boot application into a Docker image.
The first step is to package the application as a JAR file:
> mvn clean package -DskipTests

Here, we first clean-up our previous builds before packaging the application. In addition, we skip the tests because they fail without PostgreSQL.

Let's run our Spring Boot application and PostgreSQL with Docker Compose:
> `docker-compose up`

###built with java 11 and maven 3.6.3