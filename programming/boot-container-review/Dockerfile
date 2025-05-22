# Stage 1: Build
FROM azul/zulu-openjdk:17-latest AS build
WORKDIR /app

# 그래들 파일 복사 및 의존성 캐싱
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x ./gradlew

# 소스 코드 복사 및 빌드
COPY src src
RUN ./gradlew build -x test

# Stage 2: Runtime
FROM azul/zulu-openjdk:17-latest
WORKDIR /app

# 빌드 스테이지에서 JAR 파일만 복사
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Spring 프로필을 'prod'로 설정
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]