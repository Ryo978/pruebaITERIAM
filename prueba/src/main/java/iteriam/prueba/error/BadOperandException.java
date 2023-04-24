package iteriam.prueba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadOperandException extends ResponseStatusException {
    public BadOperandException(String message){
        super (HttpStatus.BAD_REQUEST, message);
    }
}
