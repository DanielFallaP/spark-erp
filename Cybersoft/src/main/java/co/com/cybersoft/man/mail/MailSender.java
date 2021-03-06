package co.com.cybersoft.man.mail;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * Sends email message to the specified address
 * @author Daniel
 *
 */
public class MailSender implements Runnable{
	
	protected MimeMessage message;
	
	public MailSender(MimeMessage message){
		this.message=message;
	}
	
	public MailSender(){
		
	}

	public void run() {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
