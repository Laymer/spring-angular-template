FROM bellsoft/liberica-openjre-alpine-musl:21
LABEL authors="Julien Bastin"

WORKDIR /app

ENV JAVA_OPTS ""
ENV SPRING_OPTS ""

COPY target/backend-*.jar app.jar

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]