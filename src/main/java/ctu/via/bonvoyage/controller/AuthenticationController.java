package ctu.via.bonvoyage.controller;

import ctu.via.bonvoyage.interfaces.UserObject;
import ctu.via.bonvoyage.interfaces.error.BadRequestException;
import ctu.via.bonvoyage.interfaces.request.JwtRequest;
import ctu.via.bonvoyage.interfaces.source.RestSource;
import ctu.via.bonvoyage.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Api(tags = "security")
class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(@NotNull @Autowired AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Authenticate user by email and password", tags = "security",
            notes = "Return user information and jwt token")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = UserObject.class),
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.SIGN_IN, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserObject createAuthenticationToken(@NotNull @Valid @RequestBody JwtRequest authenticationRequest) {
        LOGGER.debug("createAuthenticationToken {}", authenticationRequest);

        return authenticationService
                .authenticateUser(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }

    @ApiOperation(value = "Register new user", tags = "security",
            notes = "Save new user to DB, return user information and jwt token")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful response", response = UserObject.class),
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.SIGN_UP, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserObject signUp(@NotNull @Valid @RequestBody UserObject userObject) {
        LOGGER.debug("signUp {}", userObject);

        return authenticationService.createUser(userObject);
    }

}