package iteriam.prueba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

public class BadOperandException extends ResponseStatusException {
    @Serial
    private static final long serialVersionUID = 2213466371148972203L;

    public BadOperandException(String message){
        super (HttpStatus.BAD_REQUEST, message);
    }
}
