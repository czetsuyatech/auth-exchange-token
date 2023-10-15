# Auth Exchange - Keycloak Google Token Exchange

This project is created for software developers who want to integrate Keycloak into their systems to
enable secure user login and token exchange with Google. Eventually, this will allow users who
signin to Keycloak to access Google APIs, such as Calendar, Youtube, Email, etc.

This version extracts the access token from Spring's OAuth2AuthenticationToken.

# How to Use

## Keycloak

A docker compose file is provide to start a custom docker container (Dockerfile.keycloak).

```
docker-compose up
```

Import the realm from the docs folder (realm-export.json).

This realm already contains the configuration for Google Identity provider. Make sure to update the
Client ID and secret.

Under the clients' tab, click web-front, and open the credentials tab. Take note of the Client's
secret.

# Project Configuration

## Application Property File

File: src/main/resources/application.yml

Set the following environment variables or modify the property file.

- KEYCLOAK_CLIENT_SECRET

## Keycloak JSON Config

File: src/main/resources/keycloak.json

Set the Keycloak's `secret` value.

# Dockerized Container

A Dockerfile is provided under the docker folder. To build the project from a docker image:

```
docker build -f ./docker/Dockerfile -t siriusacm/sens-data-access-proto
```

# Testing Steps

1. Run the docker compose file in the docker folder.

```
docker-compose up
```

2. Run the application.
3. Open your web browser and navigate to http://localhost:8081, it will redirect to Keycloak login page.
4. Click "Save token". It will store the access and refresh token in the database.
5. Click "Calendar entries". It will generate a new access token from the refresh token stored in the database. This
   access token will be exchanged for Google access token to be able to call the Google API.
6. A job can be run to generate a new refresh token.

# References

- https://github.com/spring-projects/spring-authorization-server/tree/main
- https://stackoverflow.com/questions/74683225/updating-to-spring-security-6-0-replacing-removed-and-deprecated-functionality
- https://stackoverflow.com/questions/74994320/keycloak-in-spring-boot-3
- https://howtodoinjava.com/spring-boot/oauth2-login-with-keycloak-and-spring-security/
- https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html
- https://stackoverflow.com/questions/71920933/how-do-i-get-the-access-token-via-the-oauth2authorizedclientservice-in-the-clien
- https://developers.google.com/gmail/api/downloads#java
- https://stackoverflow.com/questions/51091376/java-client-to-refresh-keycloak-token
- https://www.keycloak.org/docs/latest/securing_apps/#_token-exchange
