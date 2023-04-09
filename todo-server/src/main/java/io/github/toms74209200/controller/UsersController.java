package io.github.toms74209200.controller;

import io.github.toms74209200.api.UsersApi;
import io.github.toms74209200.config.ServerConfig;
import io.github.toms74209200.domain.Email;
import io.github.toms74209200.model.PostUsers201Response;
import io.github.toms74209200.model.UserCredentials;
import io.github.toms74209200.repository.RepositoryException;
import io.github.toms74209200.service.UsersService;
import java.net.URI;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@Controller
public class UsersController implements UsersApi {

    private static final String API_PATH = "/users";

    private final NativeWebRequest request;
    private final String apiUrl;
    private final UsersService usersService;

    public UsersController(
            NativeWebRequest request, ServerConfig serverConfig, UsersService usersService) {
        this.request = request;
        this.usersService = usersService;
        this.apiUrl = serverConfig.domain() + API_PATH;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.of(request);
    }

    @Override
    public ResponseEntity<PostUsers201Response> postUsers(UserCredentials userCredentials) {
        log.info("{}", userCredentials);
        Email email;
        try {
            email = Email.of(userCredentials.getEmail());
        } catch (IllegalArgumentException e) {
            log.info("Invalid email: {}", userCredentials.getEmail());
            return ResponseEntity.badRequest().build();
        }

        if (userCredentials.getPassword() == null || userCredentials.getPassword().isEmpty()) {
            log.info("Invalid password: {}", userCredentials.getPassword());
            return ResponseEntity.badRequest().build();
        }

        try {
            long userId = usersService.postUsers(email, userCredentials.getPassword());
            URI location = URI.create(apiUrl + "/" + userId);
            return ResponseEntity.created(location)
                    .body(PostUsers201Response.builder().id(userId).build());
        } catch (RepositoryException e) {
            log.warn("", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
