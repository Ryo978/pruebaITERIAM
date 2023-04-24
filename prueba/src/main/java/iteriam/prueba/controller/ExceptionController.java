package iteriam.prueba.controller;

import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    /*
     * Verificamos que si no hay un + o un - no haya problemas
     */
    @ExceptionHandler(BadOperatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BadOperatorException> handleBadOperatorException(BadOperatorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    /*
     * Salta si ha habido algún problema con los operandos.
     */
    @ExceptionHandler(BadOperandException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BadOperandException> handleBadOperandException(BadOperandException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }


}
