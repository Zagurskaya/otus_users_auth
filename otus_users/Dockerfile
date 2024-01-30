FROM bellsoft/liberica-openjdk-debian:14

ARG RP_CONTAINER_IMAGE_TAG

ENV SENTRY_RELEASE=$RP_CONTAINER_IMAGE_TAG

ENV LANG C.UTF-8

WORKDIR /app

COPY build/libs/app.jar app.jar

RUN adduser --system --group app && chown -R app:app .

USER app:app

ENTRYPOINT ["java",\
            "-Djava.security.egd=file:/dev/./urandom",\
            "-Duser.timezone=Europe/Moscow",\
            "-jar", "app.jar"]

EXPOSE 8000
