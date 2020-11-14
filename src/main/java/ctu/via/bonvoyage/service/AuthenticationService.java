package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.configuration.security.JwtTokenUtil;
import ctu.via.bonvoyage.interfaces.UserObject;
import ctu.via.bonvoyage.interfaces.entity.UserEntity;
import ctu.via.bonvoyage.interfaces.repository.UserRepository;
import ctu.via.bonvoyage.interfaces.response.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService, AuthenticationManager {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(@NotNull @Autowired UserRepository userRepository,
                                 @NotNull @Autowired JwtTokenUtil jwtTokenUtil,
                                 @NotNull @Autowired PasswordEncoder passwordEncoder,
                                 @NotNull @Autowired Mapper mapper){
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        LOGGER.debug("loadUserByUsername {}", email);

        List<UserEntity> userEntities = userRepository.findByEmailIgnoreCaseAndValidIsTrue(email);
        if (userEntities.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "User with email "+ email + "not found!");
        }

        return new User(userEntities.get(0).getEmail(), userEntities.get(0).getPasswordHash(), new ArrayList<>());
    }

    public UserObject authenticateUser(String email, String password) {
        LOGGER.debug("authenticateUser {} {}", email, password);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        authenticate(authentication);

        return createUserObject(email);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.debug("authenticate {}", authentication);

        final UserDetails userDetails = loadUserByUsername(authentication.getPrincipal().toString());

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Bad credentials!");
        }

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        return authentication;
    }

    public UserObject createUser(UserObject userObject){
        LOGGER.debug("createUser {}", userObject);

        checkEmail(userObject.getEmail());

        UserEntity userEntity = mapper.map(userObject, UserEntity.class);
        userEntity.setPasswordHash(passwordEncoder.encode(userObject.getPassword()));
        userEntity.setValid(true);
        userRepository.save(userEntity);

        return setTokenAndGrantedAuthorities(new User(userEntity.getEmail(), userEntity.getPasswordHash(),
                        new ArrayList<>()), mapper.map(userEntity, UserObject.class));
    }

    private UserObject setTokenAndGrantedAuthorities(UserDetails userDetails, UserObject userObject){
        final String token = jwtTokenUtil.generateToken(userDetails);

        userObject.setJwtResponse(new JwtResponse(token));
        userObject.setGrantedAuthorities(userDetails.getAuthorities());

        return userObject;
    }

    public UserObject createUserObject(String email){
        List<UserEntity> userEntities = userRepository
                .findByEmailIgnoreCaseAndValidIsTrue(email);

        UserObject userObject = new UserObject();

        if (!userEntities.isEmpty()){
            userObject = setTokenAndGrantedAuthorities(new User(userEntities.get(0).getEmail(),
                            userEntities.get(0).getPasswordHash(), new ArrayList<>()),
                            mapper.map(userEntities.get(0), UserObject.class));
        }
        return userObject;
    }

    private void checkEmail(String email){
        List<UserEntity> userEntities = userRepository.findByEmailIgnoreCaseAndValidIsTrue(email);

        if (!userEntities.isEmpty() ){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email is already in db!");
        }
    }

}