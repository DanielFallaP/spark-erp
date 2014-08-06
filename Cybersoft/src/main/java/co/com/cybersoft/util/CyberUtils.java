package co.com.cybersoft.util;

import java.util.Arrays;
import java.util.List;

public class CyberUtils {

	public static final String arraySeparator="/////";
	
	public final static String decisionTableTrueValue="label.true";
	
	public final static String decisionTableFalseValue="label.false";
	
	public final static String idField ="id";
	
	public final static String excelFilePath="../webapps/Spark/resources/excel";
	
	public final static String excelURLPath="/resources/excel";
	
	public final static String notEmptyValidation="NotEmpty";
	
	public final static String notNullValidation="NotNull";
	
	public final static String typeValidation="typeMismatch";
	
	public final static String rangeValidation="Range";
	
	public final static String lengthValidation="Length";
	
	public final static String configTableName="tenancy";
	
	public final static String englishLocale="en";
	
	public final static String messageResourcePrefix="label.";

	public final static String defaultCreationDateName="dateOfCreation";
	
	public final static String defaultModificationDateName="dateOfModification";
	
	public final static String defaultModifyingUser="userName";
	
	public final static String defaultCreatingUser="createdBy";
	
	public final static String setMethodPrefix="set";
	
	public final static String additionForm="additionForm";
	
	public final static String modificationForm="modificationForm";
	
	private final static List<Character> PCREEscapeCharacters=Arrays.asList('.','^','$','*','+','?','(',')','[','{','|','^','-',']');
	
	public final static String stringLiteralEscape="\\\\";
	
	public final static String countersCollection="counters";
	
	public final static String dataBaseName="cybersoft";

	public final static String getNextSequence="function getNextSequence(name) {   var ret = db.counters.findAndModify(          {            query: { _id: name },            update: { $inc: { seq: 1 } },            new: true          }   );   return ret.seq;}";
	
	public static String escapePCRECharacters(String string){
		for (Character escapeCharacter : PCREEscapeCharacters) {
			string=string.replace(escapeCharacter.toString(), stringLiteralEscape+escapeCharacter);
		}
		return string;
	}
	
}
