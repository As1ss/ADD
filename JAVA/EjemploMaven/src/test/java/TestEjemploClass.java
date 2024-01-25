import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestEjemploClass extends MultiplicarTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testSumar() {
		assertEquals(2, 1+1);
	}
	
	
	@Test
	void testRestar() {
		assertEquals(4,10-6);
	}
	
	
	
	

}
