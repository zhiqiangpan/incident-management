# Incident Management System
 
## 项目简介
 
这是一个简单的事件管理应用，它使用Spring Boot框架创建一个基本的RESTful API服务。该应用程序将列出已存在的事件，并且用户可以增加、删除和修改事件。 
## 技术栈
 
- **后端**：Spring Boot
- **构建工具**：Maven
- **运行时环境**：OpenJDK 17
- **容器化**：Docker

## 外部库

- Spring Boot Web Starter (spring-boot-starter-web):
这是Spring Boot框架的一部分，提供了构建Web应用程序所需的所有核心依赖项，如Spring MVC和Tomcat（或其他嵌入式servlet容器）。
- Spring Boot Test Starter (spring-boot-starter-test):
这是Spring Boot的测试依赖项集合，包括了JUnit、Mockito等测试框架，以及Spring TestContext Framework等Spring特有的测试支持。
- Spring Boot Starter Cache (spring-boot-starter-cache):
提供了对缓存抽象的支持，允许您轻松地添加基于缓存的解决方案（如Caffeine、Ehcache、Redis等）到您的Spring Boot应用程序中。
- Lombok (lombok):
Lombok是一个Java库，它通过注解的方式自动化地为您的Java类生成如getter/setter、equals、hashCode、toString等方法，以及构造函数等。
- Validation API (validation-api):
这是Java Bean Validation 1.1和2.0的API规范，它提供了一套用于对Java Bean进行约束声明和验证的注解和API。
## 构建与运行
 
### 构建项目
 
1. 确保你已经安装了Maven和JDK 17。
2. 克隆项目到本地。
3. 在项目根目录下运行 `mvn clean package` 命令来构建项目。
 
### 使用Docker运行
 
1. 确保你已经安装了Docker。
2. 在项目根目录下找到 `Dockerfile`。
3. 构建Docker镜像：运行 `docker build -t incident-management .` 命令。
4. 运行Docker容器：使用 `docker run -d -p 8080:8080 incident-management` 命令来启动容器。
5. 打开浏览器，访问 `http://localhost:8080` 以查看应用程序。
 
### 注意事项
 
- 默认情况下，应用程序运行在8080端口上。如果你需要更改它，请在 `application.properties` 文件中设置 `server.port` 属性。
- 在构建Docker镜像之前，请确保 `target` 目录下存在名为 `incident-management-*.jar` 的JAR文件。这是由Maven构建过程生成的。
 

 
