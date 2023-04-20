package iteriam.prueba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class BadOperatorException extends ResponseStatusException {
    public BadOperatorException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
