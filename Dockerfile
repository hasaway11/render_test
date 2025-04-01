# 1. 베이스 이미지 (Gradle 포함된 JDK 17 사용)
FROM gradle:7.5.1-jdk17 AS builder

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 프로젝트 파일 복사
COPY --chown=gradle:gradle . .

# 4. Gradle 빌드 실행
RUN chmod -R 777 /home/gradle/.gradle
RUN gradle clean build --no-daemon --refresh-dependencies --no-build-cache


# 5. 실행할 JAR 파일을 가져오는 단계
FROM azul/zulu-openjdk:17
WORKDIR /app
COPY --from=builder /app/build/libs/demo3-0.0.1-SNAPSHOT.jar app.jar

# 6. 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]