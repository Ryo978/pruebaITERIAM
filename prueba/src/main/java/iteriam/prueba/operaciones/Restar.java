package iteriam.prueba.operaciones;

import iteriam.prueba.error.BadOperandException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("restar")
public class Restar implements Operation {

    @Override
    public BigDecimal calculate(BigDecimal a, BigDecimal b) throws BadOperandException {
        if (hasInvalidOperands(a,b)) throw new BadOperandException("Uno de los operandos no es válido. Es imposible hacer la operación.");
        return  a.subtract(b);
    }

    private boolean hasInvalidOperands (BigDecimal a, BigDecimal b){
        return a == null || b == null;
    }
}
