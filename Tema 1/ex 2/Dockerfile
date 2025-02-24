# Folosim imaginea OpenJDK 11 ca bază
FROM openjdk:11-jdk-slim

# Instalăm git și verificăm că s-a instalat corect
RUN apt-get update && apt-get install -y git && \
    git --version && javac -version && rm -rf /var/lib/apt/lists/*

# Setăm directorul de lucru în container
WORKDIR /app

# Copiem fișierele aplicației
COPY src/TextProcessor.java .
COPY src/input.txt .

# Inițializăm repository-ul Git și configurăm identitatea utilizatorului
RUN git init && \
    git config --global user.email "docker@example.com" && \
    git config --global user.name "Docker Container" && \
    git add TextProcessor.java input.txt && \
    git commit -m "Initial commit: Adăugat fișierul de procesare a textului"

# Compilăm aplicația separat (evităm să rulăm totul într-o singură linie)
RUN javac TextProcessor.java

# Aplicația se va rula la pornirea containerului
CMD ["java", "TextProcessor"]
