# Usa una imagen de OpenJDK ligera
FROM mcr.microsoft.com/openjdk/jdk:21

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia tu JAR compilado al contenedor
COPY target/*.jar app.jar

# Expón el puerto que usará la app (ejemplo 8080, cambia si usas otro)
EXPOSE 5478

# Comando para ejecutar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]
