package co.com.cybersoft.man.services.purchaseOrder;

import java.io.File;
import java.io.InputStream;

import javax.mail.Transport;

import org.antlr.stringtemplate.StringTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import co.com.cybersoft.docs.persistence.domain.PurchaseOrder;
import co.com.cybersoft.docs.persistence.domain.PurchaseOrderBody;
import co.com.cybersoft.docs.web.domain.purchaseOrder.PurchaseOrderInfo;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.tables.persistence.domain.ThirdParty;
import co.com.cybersoft.tables.persistence.domain.User;
import co.com.cybersoft.util.CyberUtils;

/**
 * Builds and sends a message to the provider with the proper purchase order
 * @author daniel
 *
 */
public class PurchaseOrderSender implements Runnable{
	
	private User user;
	
	private Long purchaseOrderId;
	
	private PurchaseOrderInfo purchaseOrder;
	
	private ThirdParty thirdParty;
	
	private ReportingService reportingService;
	
	public PurchaseOrderSender(User user, Long purchaseOrderId, PurchaseOrderInfo purchaseOrder, ThirdParty thirdParty, ReportingService reportingService){
		this.user=user;
		this.purchaseOrder=purchaseOrder;
		this.purchaseOrderId=purchaseOrderId;
		this.thirdParty=thirdParty;
		this.reportingService=reportingService;
	}
	
	@Override
	public void run() {
		try {
			File docToExcelFile = reportingService.docToExcelFile(PurchaseOrder.class.getCanonicalName(), PurchaseOrderBody.class.getCanonicalName(), LocaleContextHolder.getLocale(), purchaseOrderId);
			
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CyberUtils.purchaseOrderMessageTemplateDir+"purchaseOrderMessage_es.st");
			
			String fileContent = CyberUtils.getTextFileContent(inputStream);
			StringTemplate template = new StringTemplate(fileContent);
			template.setAttribute("thirdParty", purchaseOrder.getThirdParty());
			template.setAttribute("user", user.getFirstNames()+" "+user.getLastName());
			
			Transport.send(new CyberUtils().getMessageWithAttachment(user.getEmail(), thirdParty.getEmail(), "Orden de compra", template.toString(), docToExcelFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
