# --- 阶段 1: "构建" ---
# 使用一个包含 Java 17 和 Maven 的标准镜像
FROM maven:3.9-eclipse-temurin-21 AS build

# 在容器内创建一个工作目录
WORKDIR /app

# 1. 先只复制 pom.xml，这样可以利用 Docker 缓存
COPY pom.xml .
# 2. 下载所有依赖
RUN mvn dependency:go-offline

# 3. 复制你的所有源代码
COPY src ./src

# 4. 构建你的 .jar 文件 (并跳过测试)
RUN mvn package -DskipTests

# --- 阶段 2: "运行" ---
# 使用一个*非常小*的、只包含 Java 17 的镜像
FROM eclipse-temurin:21-jre

# 在新镜像中创建工作目录
WORKDIR /app

# 从 "构建" 阶段，把编译好的 .jar 文件复制过来
#
# !!! 重要 !!!
# 检查你的 pom.xml 里的 <artifactId> 和 <version>
# 确保下面的 .jar 文件名是正确的！
COPY --from=build /app/target/yuuphoria-0.0.1-SNAPSHOT.jar app.jar

# 告诉 DigitalOcean 你的程序会监听 8080 端口
EXPOSE 8080

# 最终运行 .jar 文件的命令
ENTRYPOINT ["java", "-jar", "app.jar"]