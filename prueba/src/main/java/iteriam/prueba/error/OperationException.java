package iteriam.prueba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OperationException extends ResponseStatusException {
    public OperationException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
