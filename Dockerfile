FROM ubuntu:latest

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

  EXPOSE 19000

  VOLUME /var/log/sampleserver

  COPY src/main/resources/data.csv data.csv
  COPY target/sample-server-*.jar server.jar
  COPY target/classes/dev-config.yml dev-config.yml
  COPY target/classes/docker-config.yml docker-config.yml

  CMD sh -c "java -jar -Xms${JAVA_PROCESS_MIN_HEAP} -Xmx${JAVA_PROCESS_MAX_HEAP} -XX:+${GC_ALGO} -Dfile.encoding=utf-8  server.jar server ${CONFIG_ENV}-config.yml"
