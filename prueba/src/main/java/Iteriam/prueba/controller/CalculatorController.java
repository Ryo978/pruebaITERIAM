package Iteriam.prueba.controller;

import Iteriam.prueba.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    /*
    * En esta llamada, devolvemos el resultado siempre que el operador sea correcto ('+' o '-') y si no, procedemos a mandar un bad request.
    */
    @GetMapping("calculator/{operand_1}/{operand_2}/{operator}")
    public ResponseEntity<Double> getResult(@PathVariable double operand_1, @PathVariable double operand_2, @PathVariable String operator){
        try {
            double result = calculatorService.resolveSimpleOperation(operand_1,operand_2,operator);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IOException e) {
          // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            //TODO: manejador de excepciones, no estoy contento con como se gestiona de esta forma.
        }
    }

}
