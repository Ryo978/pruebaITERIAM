package iteriam.prueba.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;


public class BadOperatorException extends ResponseStatusException {
    @Serial
    private static final long serialVersionUID = -4460140707538728714L;

    public BadOperatorException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
