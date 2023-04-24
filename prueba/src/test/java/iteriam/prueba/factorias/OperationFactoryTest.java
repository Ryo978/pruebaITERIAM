package iteriam.prueba.factorias;

import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.operaciones.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OperationFactoryTest {

    @Autowired
    OperationFactory factory;

    @Mock
    Map<String, Operation> operaciones;
    @Mock
    Operation operacion;

    @Test
    void calculateOk(){
        BigDecimal expected = new BigDecimal(20.7);
        when(operacion.calculate(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(expected);
        when(operaciones.containsKey(anyString())).thenReturn(true);
        when(operaciones.get(anyString())).thenReturn(operacion);
        BigDecimal actual = factory.calculate(new BigDecimal(12.7), new BigDecimal(8), "sumar");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void calculateNotOkByOperator(){
        when(operaciones.containsKey(anyString())).thenReturn(false);
        Assertions.assertThrows(BadOperatorException.class, () -> {
            factory.calculate(new BigDecimal(12.7), new BigDecimal(8), "dividir");
        });
    }

    @Test
    void calculateNotOkByOperand(){
        when(operaciones.containsKey(anyString())).thenReturn(true);
        when(operaciones.get(anyString())).thenReturn(operacion);
        when(operacion.calculate(any(BigDecimal.class), any(BigDecimal.class)))
                .thenThrow(new BadOperandException("No se reconoce un operando"));
        Assertions.assertThrows(BadOperandException.class, () -> {
            factory.calculate(null, new BigDecimal(8), "sumar");
        });
    }

    @Test
    void listAllOperatorTest(){
        Set<String> set = new HashSet<>();
        Collections.addAll(set, "sumar", "restar");
        when(operaciones.keySet()).thenReturn(set);
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, "sumar", "restar");
        List<String> actual = factory.listOperationTypes();
        Assertions.assertTrue(actual.size() == expected.size());
    }
}
