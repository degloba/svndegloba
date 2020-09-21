# Copyright 2015 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#FROM adoptopenjdk/openjdk11:latest
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8888


# https://springframework.guru/manage-docker-containers-with-docker-compose/
FROM openjdk:11
ADD ./target/ecommerce-webapp-4.4.3-SNAPSHOT.jar /usr/src/ecommerce-webapp-4.4.3-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "ecommerce-webapp-4.4.3-SNAPSHOT.jar"]
EXPOSE 8888


#ecommerce-webapp-4.4.3-SNAPSHOT.jar