- Project started from https://spring.io/guides/gs/messaging-rabbitmq/


##################################################
# To add all the necessary Maven Wrapper files
#
# This is only run once and has already been run.
##################################################
mvn -N io.takari:maven:wrapper


##################################################
# To build
##################################################
./mvnw clean package


##################################################
# To run
##################################################
java -jar target/amqpExample-0.1.0.jar

or

./mvnw spring-boot:run
