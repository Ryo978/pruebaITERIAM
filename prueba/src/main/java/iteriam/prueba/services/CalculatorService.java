package iteriam.prueba.services;


import iteriam.prueba.error.BadOperatorException;
import io.corp.calculator.TracerImpl;
import iteriam.prueba.error.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.IntStream;


@Service
public class CalculatorService {


    @Autowired
    TracerImpl tracer;

    public double resolveSimpleOperation(double operand1, double operand2, String operator) throws BadOperatorException {
        double result=0;
        switch (operator){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            default:
                throw new BadOperatorException("No se reconoce el operador.");
        }
        tracer.trace(result);
        return result;
    }

    public double resolveComplexOperation(String operation) throws OperationException{
        String op = operation.trim().replaceAll("\\s", "");
        if (!op.matches("[\\d\\+\\-\\.]+"))
            throw new OperationException("La cadena contiene carácteres no permitidos");
        int[] indices = IntStream.range(0, op.length())
                .filter(i -> (op.charAt(i) == '+') || (op.charAt(i) == '-'))
                .toArray();
        return makeComplexOperations(op, indices);
    }

    private double makeComplexOperations(String operation, int[] indices) throws OperationException{
        int previousindice = indices[0];
        double result = 0.0;
        try {
            result = Double.valueOf(operation.substring(0, indices[0]));
            double operand;
            for (int i = 0; i < indices.length; i++) {
                if (i+1 >= indices.length)
                    operand = Double.valueOf(operation.substring(indices[i]+1));
                else
                    operand = Double.valueOf(operation.substring(indices[i], indices[i+1]));
                result = resolveSimpleOperation(result, operand, String.valueOf(operation.charAt(indices[i])));
            }
        } catch (Exception e) {
            throw new OperationException("Ha habido un problema al obtener un operando.");
        }
        tracer.trace(result);
        return result;
    }







}
