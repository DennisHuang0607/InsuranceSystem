# 使用 Eclipse Temurin 的 JDK 17 作為基底映像
FROM eclipse-temurin:17-jdk

# 設定容器工作目錄
WORKDIR /app

# 把 Maven build 出來的 JAR 放進容器
#COPY target/insurancesystem.jar app.jar  -> 改為萬用字元*
COPY target/*.jar app.jar

# 宣告使用的 Port（只是文件用途，不做映射）
EXPOSE 8080

# 啟動 Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]