package iteriam.prueba.services;

import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.factorias.OperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    CalculatorService service;

    @Mock
    OperationFactory factory;

    @Test
    void resolveOperationOk(){
        BigDecimal expected = new BigDecimal(20.7);
        when(factory.calculate(any(BigDecimal.class), any(BigDecimal.class), anyString())).thenReturn(expected);
        BigDecimal actual = service.resolveOperation(new BigDecimal(12.7), new BigDecimal(8), "sumar")
                .getResult();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void resolveOperationNotOkByOperator(){
        BigDecimal a = new BigDecimal(12.7);
        BigDecimal b = new BigDecimal(8);
        when(factory.calculate(any(BigDecimal.class), any(BigDecimal.class), anyString()))
                .thenThrow(new BadOperatorException("Error"));
        Assertions.assertThrows(BadOperatorException.class, () -> {
            service.resolveOperation(a, b, "dividir");
        });
    }

    @Test
    void resolveOperationNotOkByOperand(){
        BigDecimal b = new BigDecimal(8);
        when(factory.calculate(any(), any(BigDecimal.class), anyString()))
                .thenThrow(new BadOperandException("Error"));
        Assertions.assertThrows(BadOperandException.class, () -> {
            service.resolveOperation(null, b, "sumar");
        });
    }

    @Test
    void getAllOperatorsTest(){
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected,"sumar", "restar");
        when(factory.listOperationTypes()).thenReturn(expected);
        List<String> actual = service.getAllOperators();
        Assertions.assertEquals(expected.size(), actual.size());
    }


}