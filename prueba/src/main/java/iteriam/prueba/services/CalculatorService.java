package iteriam.prueba.services;


import iteriam.prueba.error.BadOperatorException;
import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
                BadOperatorException ex = new BadOperatorException("No se reconoce el operador.");
                tracer.trace(ex);
                throw ex;
        }
        tracer.trace(result);
        return result;
    }

}
