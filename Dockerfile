# 1. 베이스 이미지 (Gradle 포함된 JDK 17 사용)
FROM gradle:7.5.1-jdk17 AS builder

# 2. 작업 디렉토리 설정
WORKDIR /app
USER root
RUN mkdir -p /home/gradle/.gradle && \
    chown -R gradle:gradle /home/gradle && \
    chmod -R 777 /home/gradle/.gradle && \
    mkdir -p /app/.gradle && \
    chown -R gradle:gradle /app/.gradle && \
    chmod -R 777 /app/.gradle

# 3. 프로젝트 파일 복사 (gradle 사용자로)
USER gradle
COPY --chown=gradle:gradle . .

# 4. Gradle 빌드 실행 (gradle 사용자로)
RUN gradle clean build --no-daemon --refresh-dependencies --stacktrace


# 5. 실행할 JAR 파일을 가져오는 단계
FROM azul/zulu-openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/demo3-0.0.1-SNAPSHOT.jar app.jar

# 6. 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]