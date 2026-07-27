FROM cbi-hawk-demo-docker-virtual.artifactory.alm.corp.hmrc.gov.uk/adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

ARG JAR_FILE=target/*.jar
ARG CI_COMMIT_SHORT_SHA
ARG CI_PIPELINE_ID

COPY ${JAR_FILE} /app/app.jar

WORKDIR /app

LABEL CI_COMMIT_SHORT_SHA=${CI_COMMIT_SHORT_SHA} \
      CI_PIPELINE_ID=${CI_PIPELINE_ID}

ENTRYPOINT ["java","-jar","app.jar"]
