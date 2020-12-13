package ctu.via.bonvoyage.interfaces.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Something went wrong!");
    }
    public BadRequestException(String message) {
        super(message);
    }

}
