package co.com.cybersoft.util;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
	
	public final static String itemMessageTemplateDir="mail/item";
	
	public static String escapePCRECharacters(String string){
		for (Character escapeCharacter : PCREEscapeCharacters) {
			string=string.replace(escapeCharacter.toString(), stringLiteralEscape+escapeCharacter);
		}
		return string;
	}
	
	public MimeMessage getSimpleMessage(String from, String to, String subject, String text) throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");     
	    props.setProperty("mail.host", "smtp.gmail.com");  
	    props.put("mail.smtp.auth", "true");  
	    props.put("mail.smtp.port", "465");  
	    props.put("mail.debug", "true");  
	    props.put("mail.smtp.socketFactory.port", "465");  
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {  
	       return new PasswordAuthentication("danielfap16@gmail.com","8thisbu6");  
	   }  
	   });
	    

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);
		
		return message;
	}
	
}
