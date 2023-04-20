package iteriam.prueba;

import iteriam.prueba.error.BadOperatorException;
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
		double addTest = 26.5;
		double result = service.resolveSimpleOperation(50.9, 24.4, "-");
		Assertions.assertEquals(addTest, result);
	}

	@Test
	void noOperatorTest(){
		Assertions.assertThrows(BadOperatorException.class, () -> {
			service.resolveSimpleOperation(50.9, 24.4, "x");
		});
	}

}
