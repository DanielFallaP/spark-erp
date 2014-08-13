package co.com.cybersoft.man.services.item;

import java.io.InputStream;
import java.util.List;

import javax.mail.Transport;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.cybersoft.docs.web.controller.requisition.RequisitionController;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.util.CyberUtils;

/**
 * Builds and sends the message for item code verification
 * @author Daniel
 *
 */
public class ItemVerificationSender implements Runnable{
	
	private List<String> items;
	
	private User user;
	
	private User reqUser;

	public ItemVerificationSender(List<String> items, User user, User reqUser){
		this.items=items;
		this.user=user;
		this.reqUser=reqUser;
	}

	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);

	@Override
	public void run() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CyberUtils.itemMessageTemplateDir+"itemVerificationMessage_es.st");
		String fileContent;
		try {
			//Build the message from template file
			fileContent = CyberUtils.getTextFileContent(inputStream);
			StringTemplate template = new StringTemplate(fileContent);
			template.setAttribute("user", user.getFirstNames());
			String itemList="";
			for (String item : items) {
				itemList+="		-"+item+".\n";
			}
			template.setAttribute("itemList", itemList);
			template.setAttribute("requestingUser", reqUser.getFirstNames()+" "+reqUser.getLastName());
			template.setAttribute("requestingUserMail", reqUser.getEmail());
			
			//Send the message
			Transport.send(new CyberUtils().getSimpleMessage(reqUser.getEmail(), user.getEmail(), "Verificación de referencias de artículos", template.toString()));
			
			LOG.debug("Email message sent to "+user.getUser()+" requested by "+reqUser.getUser()+" regarding item verification");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
