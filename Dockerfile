# Builder
FROM eclipse-temurin:17-jdk as builder

WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw --batch-mode clean package -DskipTests

# Runner
FROM eclipse-temurin:20-jdk

COPY --from=builder /workspace/app/target/spring-petclinic*.jar /spring-petclinic.jar

ENV JAVA_TOOL_OPTIONS=""
ENTRYPOINT ["java","-jar","/spring-petclinic.jar"]
