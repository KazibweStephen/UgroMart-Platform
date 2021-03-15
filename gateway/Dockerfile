FROM openjdk:11
#RUN addgroup -S spring && spring -S spring -G spring
#USER spring:spring
MAINTAINER kstephen3@gmail.com
copy platform/target/platform-0.0.1-SNAPSHOT.jar platform-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","platform-0.0.1-SNAPSHOT.jar"]