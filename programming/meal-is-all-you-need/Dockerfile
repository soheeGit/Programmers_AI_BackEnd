# Maven 빌드 스테이지
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

# pom.xml만 복사하여 의존성 다운로드
COPY pom.xml .
RUN mvn dependency:go-offline

# 소스 코드 복사 및 빌드
COPY src ./src
RUN mvn clean package -DskipTests

# Tomcat 배포 스테이지
FROM tomcat:10-jre17-temurin

# 빌드된 WAR 파일을 Tomcat의 webapps 디렉토리에 복사
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]