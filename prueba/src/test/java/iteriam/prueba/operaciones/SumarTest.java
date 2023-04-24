package iteriam.prueba.operaciones;

import iteriam.prueba.error.BadOperandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class SumarTest {

    @Autowired
    Sumar sumar;

    @Test
    void sumarOk(){
        BigDecimal expected = new BigDecimal(20.7);
        BigDecimal actual = sumar.calculate(new BigDecimal(12.7), new BigDecimal(8));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sumarNotOk(){
        Assertions.assertThrows(BadOperandException.class, () -> {
            sumar.calculate(null, new BigDecimal(8));
        });
    }
}
