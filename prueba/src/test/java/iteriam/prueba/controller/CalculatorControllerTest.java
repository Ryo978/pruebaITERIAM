package iteriam.prueba.controller;

import iteriam.prueba.entities.ResultOperation;
import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class CalculatorControllerTest {

    @Autowired
    CalculatorController controller;
    @Mock
    CalculatorService calculator;

    @Test
    void getResultOk(){
        BigDecimal expected = new BigDecimal(20.7);
        when(calculator.resolveOperation(any(BigDecimal.class), any(BigDecimal.class), anyString())).thenReturn(new ResultOperation(expected));
        ResultOperation actual = controller.getResult(new BigDecimal(12.7), new BigDecimal(8), "sumar").getBody();
        Assertions.assertEquals(expected, actual.getResult());
    }

    @Test
    void getResultNotOkByOperator(){
        BigDecimal a = new BigDecimal(12.7);
        BigDecimal b = new BigDecimal(8);
        when(calculator.resolveOperation(any(BigDecimal.class), any(BigDecimal.class), anyString())).thenThrow(new BadOperatorException("Error"));
        Assertions.assertThrows(BadOperatorException.class, () -> {
            controller.getResult(a, b, "dividir");
        });
    }
    @Test
    void getResultNotOkByOperand(){
        BigDecimal b = new BigDecimal(8);
        when(calculator.resolveOperation(any(), any(BigDecimal.class), anyString())).thenThrow(new BadOperandException("Error"));
        Assertions.assertThrows(BadOperandException.class, () -> {
            controller.getResult(null, b, "sumar");
        });
    }

    @Test
    void getOperatorsTest(){
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected,"sumar", "restar");
        when(calculator.getAllOperators()).thenReturn(expected);
        List<String> actual = controller.getOperators().getBody();
        Assertions.assertEquals(expected.size(), actual.size());
    }


}
