FROM openjdk:11
#RUN addgroup -S spring && spring -S spring -G spring
#USER spring:spring
MAINTAINER kstephen3@gmail.com
copy paymentsms/target/payments-0.0.1-SNAPSHOT.jar payments-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS=
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar payments-0.0.1-SNAPSHOT.jar"]