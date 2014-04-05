package co.com.cybersoft.generator.code.util;

public class CodeUtil {
	public static String toCamelCase(String name){
		Character character= name.charAt(0);
		return character.toString().toUpperCase()+name.substring(1);
	}
}
