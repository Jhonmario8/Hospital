# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Establecer directorio de trabajo
WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Compilar el proyecto con Maven
RUN ./mvnw clean package -DskipTests

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8080

# Ejecutar el jar compilado
CMD ["java", "-jar", "target/Hospital-0.0.1-SNAPSHOT.jar"]
