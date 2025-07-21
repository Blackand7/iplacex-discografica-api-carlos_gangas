# Etapa 1: Construcción con Gradle
FROM gradle:8.5-jdk21 AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración primero (para mejor cache de Docker)
COPY build.gradle settings.gradle ./

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN gradle clean build --no-daemon -x test

# Etapa 2: Ejecución con OpenJDK
FROM openjdk:21-jre-slim

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR construido desde la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]