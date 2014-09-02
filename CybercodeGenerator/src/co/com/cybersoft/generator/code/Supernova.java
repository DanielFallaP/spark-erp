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
//			tableCodeGenerator.generate();
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
