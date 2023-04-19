package Iteriam.prueba.services;


import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CalculatorService {


    @Autowired
    TracerImpl tracer;

    public double resolveSimpleOperation(double operand_1, double operand_2, String operator) throws IOException {
        double result=0;
        switch (operator){
            case "+":
                result = operand_1+operand_2;
                break;
            case "-":
                result = operand_1-operand_2;
                break;
            default:
                throw new IOException("No se reconoce el operador.");
        }
        tracer.trace(result);
        return result;
    }

    public double operation(String operation){
        String op = operation.trim();
        op = op.replace(' ',Character.MIN_VALUE);
        return resolveOperation(op);
    }

    public double resolveOperation( String operation){

        int indexOperatorAdd = operation.lastIndexOf("+");
        int indexOperatorLess = operation.lastIndexOf("-");
        if (indexOperatorLess == indexOperatorAdd){
            return Double.valueOf(operation);
        }
        double operand;
        double result = 0.0;
        String newOperation;
        int operator;
        operator = indexOperatorAdd > indexOperatorLess ? 0 : 1;
        if (operator == 0){
            operand = Double.valueOf(operation.substring(indexOperatorAdd+1));
            newOperation = operation.substring(0, indexOperatorAdd-1);
            result = resolveOperation(newOperation) + operand;
        } else {
            operand = Double.valueOf(operation.substring(indexOperatorLess + 1));
            newOperation = operation.substring(0, indexOperatorAdd - 1);
            result = resolveOperation(newOperation) - operand;
        }
        tracer.trace(result);
        return result;
    }
}
