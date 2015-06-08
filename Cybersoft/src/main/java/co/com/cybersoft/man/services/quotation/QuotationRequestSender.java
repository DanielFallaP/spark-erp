package co.com.cybersoft.man.services.quotation;

import java.io.InputStream;
import java.util.List;

import javax.mail.Transport;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.cybersoft.purchase.docs.web.controller.requisition.RequisitionController;
import co.com.cybersoft.purchase.tables.persistence.domain.User;
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
		
		try {
			String fileContent = CyberUtils.getTextFileContent(inputStream);
			StringTemplate template = new StringTemplate(fileContent);
			template.setAttribute("thirdParty", thirdParty.getSupplier());
			template.setAttribute("user", user.getFirstNames()+" "+user.getLastName());
			String itemList="";
			List<RequiredItem> items = thirdParty.getItems();
			for (RequiredItem requiredItem : items) {
				itemList+="		-Artículo: "+requiredItem.getItemDescription()+", Cantidad:"+requiredItem.getRequiredQuantity()+" "+requiredItem.getUnit()+"\n";
			}
			template.setAttribute("items", itemList);
			
			Transport.send(new CyberUtils().getSimpleMessage(user.getEmail(), thirdParty.getSupplierEmail(), "Cotización de artículos", template.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
