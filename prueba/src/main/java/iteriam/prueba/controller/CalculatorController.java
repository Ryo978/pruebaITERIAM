package iteriam.prueba.controller;

import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.error.OperationException;
import iteriam.prueba.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    /*
    * En esta llamada, devolvemos el resultado siempre que el operador sea correcto ('+' o '-') y si no, procedemos a mandar un bad request.
    */
    @GetMapping("calculator/{operand1}/{operand2}/{operator}")
    public ResponseEntity<Double> getResult(@PathVariable double operand1, @PathVariable double operand2, @PathVariable String operator){
        double result = calculatorService.resolveSimpleOperation(operand1,operand2,operator);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("calculator/{operation}")
    public ResponseEntity<Double> getResultComplexOperation(@PathVariable String operation){
        double result = calculatorService.resolveComplexOperation(operation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
     * Tratamos todos los errores que puedan venir del String operation
     */
    @ExceptionHandler(OperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> HandleOperationException(OperationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /*
    * Con este exceptionHandler verificamos que si no hay un + o un - no haya problemas
    */
    @ExceptionHandler(BadOperatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadOperatorException(BadOperatorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
