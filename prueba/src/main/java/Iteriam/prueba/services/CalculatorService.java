package Iteriam.prueba.services;


import java.io.IOException;

public class CalculatorService {

    private static CalculatorService instance;
    public static CalculatorService getInstance(){
        if (instance == null)
            instance = new CalculatorService();
        return instance;
    }

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
        return result;
    }
}
