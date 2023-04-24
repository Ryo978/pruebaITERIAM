package iteriam.prueba.controller;

import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
class ExceptionControllerTest {

    @Autowired
    ExceptionController controller;
    @Test
    void badOperatorCreated(){
        BadOperatorException exception = new BadOperatorException("El operador no existe.");
        ResponseEntity<BadOperatorException> handled = controller.handleBadOperatorException(exception);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, handled.getStatusCode());
        Assertions.assertEquals(handled.getBody(), exception);
    }

    @Test
    void badOperandCreated(){
        BadOperandException exception = new BadOperandException("Ha ocurrido un problema con el operando.");
        ResponseEntity<BadOperandException> handled = controller.handleBadOperandException(exception);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, handled.getStatusCode());
        Assertions.assertEquals(handled.getBody(), exception);
    }
}
