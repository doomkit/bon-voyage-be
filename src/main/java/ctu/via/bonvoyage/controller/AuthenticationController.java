package ctu.via.bonvoyage.controller;

import ctu.via.bonvoyage.interfaces.UserObject;
import ctu.via.bonvoyage.interfaces.request.JwtRequest;
import ctu.via.bonvoyage.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(@NotNull @Autowired AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "public/authenticate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserObject createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        LOGGER.debug("createAuthenticationToken {}", authenticationRequest);

        return authenticationService
                .authenticateUser(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }

    @RequestMapping(value = "public/signUp", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserObject signUp(@NotNull @Valid @RequestBody UserObject userObject) {
        LOGGER.debug("signUp {}", userObject);

        return authenticationService.createUser(userObject);
    }

}