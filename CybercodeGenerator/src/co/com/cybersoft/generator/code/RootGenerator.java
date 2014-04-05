package co.com.cybersoft.generator.code;

/**
 * This class is the main class for generating all
 * CRUD operations conforming to the reference architecture
 * of CYBERSOFT
 * @author Daniel Falla, Cybersoft 
 *
 */
public class RootGenerator {

	public static void main(String[] args) {
		CodeGenerator codeGenerator = new CodeGenerator();
		try {
			codeGenerator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====Finished generating app");
	}

}
