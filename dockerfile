# Etapa de construcción: Usamos Maven con OpenJDK 22 para compilar el proyecto
FROM maven:3.6.3-openjdk-17 AS build
# Definimos el directorio de trabajo
WORKDIR /home/app

# Copiamos todos los archivos del proyecto al contenedor
COPY . /home/app

# Ejecutamos el build y generamos el archivo JAR
RUN mvn clean package -DskipTests

# Etapa de ejecución: Usamos OpenJDK 22 para ejecutar la aplicación Spring Boot
FROM openjdk:17-jdk

# Exponemos el puerto en el que la app estará disponible (por defecto, Spring Boot usa 8080)
EXPOSE 8080

# Copiamos el archivo JAR desde la etapa anterior al contenedor
COPY --from=build /home/app/target/*.jar /app.jar

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
