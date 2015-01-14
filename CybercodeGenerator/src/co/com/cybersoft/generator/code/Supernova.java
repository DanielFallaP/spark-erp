package co.com.cybersoft.generator.code;

import java.util.Date;

/**
 * LET THERE BE CODE
 * @author Daniel Falla, Cybersystems
 *
 */
public class Supernova {

	public static void main(String[] args) {
		TableCodeGenerator tableCodeGenerator = new TableCodeGenerator();
		DocCodeGenerator docCodeGenerator = new DocCodeGenerator();
		ConfigCodeGenerator configCodeGenerator = new ConfigCodeGenerator();
		try {
			tableCodeGenerator.generate();
			configCodeGenerator.generate();
			docCodeGenerator.generate();
			System.out.println("");
			System.out.println("");
			System.out.println("APPLICATION BUILT SUCCESSFULLY: "+new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
