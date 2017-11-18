FROM node:latest AS ui
WORKDIR /usr/src/wishlist/react-ui
COPY react-ui .
RUN npm install
RUN npm run build

FROM maven:latest AS server
WORKDIR /usr/src/wishlist
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM java:8-jdk-alpine
RUN adduser -Dh /home/tom tom
WORKDIR /static
COPY --from=ui /usr/src/wishlist/react-ui/build/ .
WORKDIR /wishlist
COPY --from=server /usr/src/wishlist/wishlist.application/target/wishlist.application-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/wishlist/wishlist.application-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=prod"]
