package iteriam.prueba;

import iteriam.prueba.error.BadOperatorException;
import iteriam.prueba.error.OperationException;
import iteriam.prueba.services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PruebaTecnicaApplicationTests {

	@Autowired
	CalculatorService service;

	@Test
	void addSuccessTest() {
		double addTest = 26.6;
		double result = service.resolveSimpleOperation(17.3, 9.3, "+");
		Assertions.assertEquals(addTest, result);
	}

	@Test
	void lessSuccessTest(){
		double lessTest = 26.5;
		double result = service.resolveSimpleOperation(50.9, 24.4, "-");
		Assertions.assertEquals(lessTest, result);
	}

	@Test
	void noOperatorTest(){
		Assertions.assertThrows(BadOperatorException.class, () -> {
			service.resolveSimpleOperation(50.9, 24.4, "x");
		});
	}

	@Test
	void complexOperationTest() {
		double operationTest = 22.9;
		double result = service.resolveComplexOperation(" 14.0+20.9- 12");
		Assertions.assertEquals(operationTest, result);
	}

	@Test
	void badRegexOperationTest(){
		Assertions.assertThrows(OperationException.class, () -> {
			service.resolveComplexOperation("a + 675.00-5*b");
		});
	}

	@Test
	void badDoubleOperationTest(){
		Assertions.assertThrows(OperationException.class, () -> {
			service.resolveComplexOperation("12+4.56+3..8");
		});
	}
}
