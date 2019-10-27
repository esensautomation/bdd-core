FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -yq --no-install-recommends wget pwgen ca-certificates software-properties-common apt-utils && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install Java.
RUN apt-get update && \
  apt-get install -y default-jdk && \
  rm -rf /var/lib/apt/lists/*

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/default-java

# install Maven

RUN cd /tmp
RUN wget https://www-us.apache.org/dist/maven/maven-3/3.6.2/binaries/apache-maven-3.6.2-bin.tar.gz
RUN tar -xvzf apache-maven-3.6.2-bin.tar.gz
RUN cp -r apache-maven-3.6.2 /opt/maven

ENV M2_HOME /opt/maven
ENV MAVEN_HOME /opt/maven

# install Google Chrome

RUN apt-get update && \
    apt-get install -y fonts-liberation libappindicator3-1 libcairo2 libgdk-pixbuf2.0-0 libgtk-3-0 libpango-1.0-0 libpangocairo-1.0-0 libxcursor1 libxss1 xdg-utils && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN dpkg -i google-chrome-stable_current_amd64.deb

ENV PATH="${PATH}:${MAVEN_HOME}/bin"
ENV PATH="${PATH}:${JAVA_HOME}/bin"

WORKDIR /usr/app

COPY . .