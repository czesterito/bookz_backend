package com.project.bookz.services.keycloak;

import com.google.common.collect.Lists;
import com.project.bookz.configuration.KeycloakRealmConfig;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.net.URI;


@Service
public class KeycloakAdminClientService {

    private String getCreatedId(Response response)
    {
        URI location = response.getLocation();
        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }

    public void addUser(String email, String username, String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        passwordCredentials.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setCredentials(Lists.newArrayList(passwordCredentials));
        user.setEnabled(true);
        Response resposne = KeycloakRealmConfig.getInstance().realm(KeycloakRealmConfig.realm).users().create(user);
        int status = resposne.getStatus();
        if (HttpStatus.CREATED.value() != status)
        {
            System.out.println("User exists");
            return;
        }
        String createdId = getCreatedId(resposne);
        CredentialRepresentation newCredential = new CredentialRepresentation();
        UserResource userResource = KeycloakRealmConfig.getInstance().realm(KeycloakRealmConfig.realm).users().get(createdId);
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(password);
        newCredential.setTemporary(false);
        userResource.resetPassword(newCredential);
    }

    public void deleteUser(String email) {
        UserRepresentation search = KeycloakRealmConfig.getInstance().realm(KeycloakRealmConfig.realm).users().search(email).get(0);
        KeycloakRealmConfig.getInstance().realm(KeycloakRealmConfig.realm).users().delete(search.getId());
    }
}

