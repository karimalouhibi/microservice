FROM openjdk:17
EXPOSE 8082
ADD target/ForumMicroserv.jar ForumMicroserv.jar
ENTRYPOINT ["java", "-jar", "ForumMicroserv.jar"]