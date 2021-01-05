package ctu.via.bonvoyage.interfaces.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Something went wrong!");
    }
    public UnauthorizedException(String message) {
        super(message);
    }

}
