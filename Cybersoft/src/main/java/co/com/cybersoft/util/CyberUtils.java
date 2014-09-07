package co.com.cybersoft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class CyberUtils {

	public static final String arraySeparator="/////";
	
	public final static String decisionTableTrueValue="label.true";
	
	public final static String decisionTableFalseValue="label.false";
	
	public final static String tableIdField ="id";
	
	public final static String docIdField="id";
	
	public final static String docStringIdField="stringId";
	
	public final static String docNumericId="numericId";
	
	public final static String bodyEntityListSuffix="BodyEntityList";
	
	public final static String docEnableDeletionField="_enableDeletion";
	
	public final static String excelFilePath="/resources/excel";
	
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
	
	public final static String itemMessageTemplateDir="mail/item/";
	
	public final static String quotationMessageTemplateDir="mail/quotation/";
	
	public final static String purchaseOrderMessageTemplateDir="mail/purchaseOrder/";

	public static final int headerColumnsPerRow = 3;
	
	public static String escapePCRECharacters(String string){
		for (Character escapeCharacter : PCREEscapeCharacters) {
			string=string.replace(escapeCharacter.toString(), stringLiteralEscape+escapeCharacter);
		}
		return string;
	}
	
	public MimeMessage getMessageWithAttachment(String from, String to, String subject, String text, File attachment) throws Exception{
		Properties props = new Properties();
		MimeMultipart multipart = new MimeMultipart();
		
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
	       return new PasswordAuthentication("danielfap16@gmail.com","987daniel");  
	   }  
	   });
	    

		MimeMessage message = new MimeMessage(session);
		MimeBodyPart bodyPart = new MimeBodyPart();
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		bodyPart.setText(text);
		
		multipart.addBodyPart(bodyPart);
		
		//Add attachment
		MimeBodyPart attach = new MimeBodyPart();
		attach.setFileName(attachment.getName());
		FileDataSource dataSource = new FileDataSource(attachment);
		attach.setDataHandler(new DataHandler(dataSource));
		multipart.addBodyPart(attach);
		
		message.setContent(multipart);
		return message;		
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
	       return new PasswordAuthentication("danielfap16@gmail.com","987daniel");  
	   }  
	   });
	    

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);
		
		return message;
	}
	
	public static String getTextFileContent(InputStream input) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line+"\n");
        }
        reader.close();
		return out.toString();
	}
	
}
