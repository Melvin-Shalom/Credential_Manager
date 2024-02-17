// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;

class GeneratorTest {
	private final Password password= new Password("Secret");
	private final Alphabet firstAlphabet = new Alphabet(true,false,false,false);
	private final Alphabet secondAlphabet = new Alphabet(false,true,true,true);
	private final Generator generator = new Generator(true,false,false,false);
	
	@Test
	void test1() {
		assertEquals("Secret", password.toString());
	}

	
	@Test
	void test2() {
		assertEquals(firstAlphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS);
	}

	@Test
	void test3() {
		assertEquals(secondAlphabet.getAlphabet(), Alphabet.LOWERCASE_LETTERS + Alphabet.NUMBERS + Alphabet.SYMBOLS);
	}
	
	@Test
	void test4() {
		assertEquals(generator.alphabet.getAlphabet(), Alphabet.UPPERCASE_LETTERS);
	}
	
	@Test
	void test5() {
		assertEquals(generator.alphabet.getAlphabet().length(), 26);
	}

	@Test
    public @interface Test {}

	private void assertEquals(int length, int i) {
		throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
	}

	private void assertEquals(String string, String string2) {
		throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
	}

}
