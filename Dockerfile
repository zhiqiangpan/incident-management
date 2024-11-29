# 使用官方的 OpenJDK 17 运行时作为基础镜像
FROM openjdk:17-jdk-slim
 
# 设置工作目录
WORKDIR /app
 
# 将 Maven 构建的项目（target/incident-management-0.0.1-SNAPSHOT.jar，版本号可能不同）复制到镜像中
COPY target/incident-management-*.jar /app/incident-management.jar
 
# 暴露应用程序运行的端口（默认是 8080）
EXPOSE 8080
 
# 定义容器启动时运行的命令
CMD ["java", "-jar", "/app/incident-management.jar"]
