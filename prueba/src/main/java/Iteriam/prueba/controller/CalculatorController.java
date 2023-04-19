package Iteriam.prueba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("calculator/{operand_1}/{operand_2}/{operator}")
    public double getResult(@PathVariable double operand_1, @PathVariable double operand_2, @PathVariable String operator){
        return 0;
    }
}
