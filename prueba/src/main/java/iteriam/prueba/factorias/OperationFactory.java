package iteriam.prueba.factorias;

import iteriam.prueba.operaciones.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class OperationFactory {

    @Autowired
    Map<String, Operation> operaciones;

    public BigDecimal calculate(BigDecimal a, BigDecimal b, String operator){
        return operaciones.get(operator).calculate(a,b);
    }

    public List<String> listOperationTypes(){
        return operaciones.keySet().stream().toList();
    }
}
