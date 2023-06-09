package iteriam.prueba.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import iteriam.prueba.entities.ResultOperation;
import iteriam.prueba.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    /*
    * En esta llamada, devolvemos el resultado siempre que el operador sea correcto ('+' o '-') y si no, procedemos a mandar un bad request.
    */
    @Operation(summary = "Resolve an operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It returns the operation solved",
                    content = @Content(mediaType = "application/json",
                        schema =  @Schema(implementation = ResultOperation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid operator supplied",
                    content = @Content) })
    @GetMapping("/calculate")
    public ResponseEntity<ResultOperation> getResult(
            @RequestParam(value = "operand1", required = false) BigDecimal operand1,
            @RequestParam(value = "operand2", required = false) BigDecimal operand2,
            @RequestParam(value = "operator") String operator){
        ResultOperation result = calculatorService.resolveOperation(operand1, operand2, operator);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    /*
    * Método para listar todos los operadores que hay disponibles en nuestra aplicación.
    */
    @Operation(summary = "Return all available operators")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "400", description = "Invalid operator supplied",
                    content = @Content) })
    @GetMapping("/getOperators")
    public ResponseEntity<List<String>> getOperators(){
        List<String> operators = calculatorService.getAllOperators();
        return new ResponseEntity<>(operators, HttpStatus.OK);
    }
}
