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



}
