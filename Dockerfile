# Use a imagem oficial do OpenJDK 11 como base
FROM openjdk:11-jre

# Copie o arquivo JAR para o contêiner
COPY target/API_GerenciadorDeEstoque-0.0.1-SNAPSHOT.jar /app/API_GerenciadorDeEstoque-0.0.1-SNAPSHOT.jar 

# Defina o diretório de trabalho como /app
WORKDIR /app

# Exponha a porta que o aplicativo Spring Boot está ouvindo 
EXPOSE 8080

# Comando para executar o aplicativo Spring Boot quando o contêiner for iniciado
CMD ["java", "-jar", "API_GerenciadorDeEstoque-0.0.1-SNAPSHOT.jar"]
