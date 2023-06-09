package iteriam.prueba.services;


import iteriam.prueba.entities.ResultOperation;
import iteriam.prueba.error.BadOperandException;
import iteriam.prueba.error.BadOperatorException;
import io.corp.calculator.TracerImpl;
import iteriam.prueba.factorias.OperationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CalculatorService {


    @Autowired
    TracerImpl tracer;

    @Autowired
    OperationFactory opFactory;

    public ResultOperation resolveOperation(BigDecimal operand1, BigDecimal operand2, String operator) throws BadOperatorException, BadOperandException {
        ResultOperation result = new ResultOperation(opFactory.calculate(operand1, operand2, operator));
        tracer.trace(result);
        return result;
    }

    public List<String> getAllOperators(){
        List<String> operators = opFactory.listOperationTypes();
        tracer.trace(operators);
        return operators;
    }

}
