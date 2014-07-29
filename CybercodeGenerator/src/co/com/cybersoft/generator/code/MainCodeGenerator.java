package co.com.cybersoft.generator.code;

import java.util.Date;

/**
 * This class is the main class for generating all
 * CRUD operations over documents and tables
 * conforming to the reference architecture
 * of CYBERSYSTEMS
 * @author Daniel Falla, Cybersystems
 *
 */
public class MainCodeGenerator {

	public static void main(String[] args) {
		TableCodeGenerator tableCodeGenerator = new TableCodeGenerator();
		DocCodeGenerator docCodeGenerator = new DocCodeGenerator();
		ConfigCodeGenerator configCodeGenerator = new ConfigCodeGenerator();
		try {
			tableCodeGenerator.generate();
			configCodeGenerator.generate();
			docCodeGenerator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("");
		System.out.println("");
		System.out.println("APPLICATION BUILT SUCCESSFULLY: "+new Date());
	}

}
