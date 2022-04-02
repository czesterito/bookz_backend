package com.project.bookz.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakRealmConfig {

    static Keycloak keycloak = null;
    final static String serverUrl = "http://keycloak:8080/auth";
    public static String realm = "Bookz";
    final static String clientId = "spring";
    final static String clientSecret = "8f41ae08-bf12-49fd-861c-9315eb99579b";
    final static String userName = "admin";
    final static String password = "password";

    public KeycloakRealmConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
        }
        return keycloak;
    }

}
