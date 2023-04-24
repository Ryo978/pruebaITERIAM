package iteriam.prueba.operaciones;

import iteriam.prueba.error.BadOperandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class RestarTest {

    @Autowired
    Restar restar;

    @Test
    void restarOk(){
        BigDecimal expected = new BigDecimal(20.7);
        BigDecimal actual = restar.calculate(new BigDecimal(28.7), new BigDecimal(8));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void restarNotOk(){
        Assertions.assertThrows(BadOperandException.class, () -> {
            restar.calculate(null, new BigDecimal(8));
        });
    }
}
