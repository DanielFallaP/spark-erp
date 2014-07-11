package co.com.cybersoft.generator.code;

/**
 * This class is the main class for generating all
 * CRUD operations conforming to the reference architecture
 * of CYBERSOFT
 * @author Daniel Falla, Cybersoft 
 *
 */
public class MainCodeGenerator {

	public static void main(String[] args) {
		TableCodeGenerator codeGenerator = new TableCodeGenerator();
		DocCodeGenerator docCodeGenerator = new DocCodeGenerator();
		try {
			codeGenerator.generate();
			docCodeGenerator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====Finished generating app");
	}

}
