package iteriam.prueba.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.error.OperationException;
import iteriam.prueba.services.CalculatorService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    /*
    * En esta llamada, devolvemos el resultado siempre que el operador sea correcto ('+' o '-') y si no, procedemos a mandar un bad request.
    */
    @Operation(summary = "Resolve a simple operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns the operation solved",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid operator supplied",
                    content = @Content) })
    @GetMapping("/{operand1}/{operand2}/{operator}")
    public ResponseEntity<Double> getResult(
            @Parameter(description = "First operand of the operation") @PathVariable double operand1,
            @Parameter(description = "Second operand of the operation") @PathVariable double operand2,
            @Parameter(description = "Operator of the operation (just '+' or '-')") @PathVariable String operator){
        double result = calculatorService.resolveSimpleOperation(operand1,operand2,operator);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Operation(summary = "Resolve multiple operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns the operation solved",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid operator supplied",
                    content = @Content) })
    @GetMapping("/{operation}")
    public ResponseEntity<Double> getResultComplexOperation(
            @Pattern(regexp = "[\\d\\+\\-\\.]+")
            @Parameter(description = "An string that contains the full operation.")
            @PathVariable String operation){
        double result = calculatorService.resolveComplexOperation(operation);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
     * Tratamos todos los errores que puedan venir del String operation
     */
    @ExceptionHandler(OperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleOperationException(OperationException e){
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
