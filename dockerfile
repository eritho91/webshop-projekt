FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

ARG GH_USERNAME
ARG GH_PACKAGES_TOKEN

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

RUN mkdir -p /root/.m2 && \
    printf '<settings>\n\
  <servers>\n\
    <server>\n\
      <id>github</id>\n\
      <username>%s</username>\n\
      <password>%s</password>\n\
    </server>\n\
  </servers>\n\
</settings>\n' "$GH_USERNAME" "$GH_PACKAGES_TOKEN" > /root/.m2/settings.xml

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]