FROM eclipse-temurin:23-jdk

WORKDIR /app

COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon

COPY src ./src

CMD ["./gradlew", "bootRun", "--no-daemon"]