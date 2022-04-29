FROM openjdk:11
MAINTAINER vlad.ilyuschenko
COPY /build/libs/fbc-bot-0.0.1-SNAPSHOT.jar /srv/fbc-bot.jar
WORKDIR /srv
ENTRYPOINT ["java","-jar","fbc-bot.jar"]