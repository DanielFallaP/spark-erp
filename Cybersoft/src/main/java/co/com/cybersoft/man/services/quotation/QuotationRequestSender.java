package co.com.cybersoft.man.services.quotation;

import java.io.InputStream;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.cybersoft.docs.web.controller.requisition.RequisitionController;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.util.CyberUtils;

/**
 * Builds and sends a message for each provider bidding for a required item
 * @author Raul
 *
 */
public class QuotationRequestSender implements Runnable{

	private User user;
	
	private QuotationSupplier thirdParty;
	
	private static final Logger LOG = LoggerFactory.getLogger(RequisitionController.class);
	
	public QuotationRequestSender(User user, QuotationSupplier thirdParty){
		this.user=user;
		this.thirdParty=thirdParty;
	}
	
	@Override
	public void run() {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CyberUtils.quotationMessageTemplateDir+"quotationRequestMessage_es.st");
//		CyberUtils.getTextFileContent(in)
		
		try {
			String fileContent = CyberUtils.getTextFileContent(inputStream);
			StringTemplate template = new StringTemplate(fileContent);
			template.setAttribute("thirdParty", thirdParty.getSupplier());
			template.setAttribute("user", user.getFirstNames()+" "+user.getLastName());
			String itemList="";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
