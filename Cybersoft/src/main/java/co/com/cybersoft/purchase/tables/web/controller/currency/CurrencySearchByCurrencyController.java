package co.com.cybersoft.purchase.tables.web.controller.currency;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.core.services.currency.CurrencyService;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class CurrencySearchByCurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping(value="/common/currency/getListByCurrencyReturnCode", method=RequestMethod.GET)
			public void getCurrencyListReturnCode(String currency, HttpServletResponse response) throws Exception{
			CurrencyPageEvent currencyEvent = currencyService.requestByContainingCurrency(currency);
			
			List<CurrencyDetails> currencyList = currencyEvent.getCurrencyList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyDetails currencyDetails : currencyList) {
				responseBuff.append(currencyDetails.getCode());
				if (i!=currencyList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currency/getListByCurrencyReturnCurrency", method=RequestMethod.GET)
			public void getCurrencyListReturnCurrency(String currency, HttpServletResponse response) throws Exception{
			CurrencyPageEvent currencyEvent = currencyService.requestByContainingCurrency(currency);
			
			List<CurrencyDetails> currencyList = currencyEvent.getCurrencyList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyDetails currencyDetails : currencyList) {
				responseBuff.append(currencyDetails.getCurrency());
				if (i!=currencyList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currency/getListByCurrencyReturnActive", method=RequestMethod.GET)
			public void getCurrencyListReturnActive(String currency, HttpServletResponse response) throws Exception{
			CurrencyPageEvent currencyEvent = currencyService.requestByContainingCurrency(currency);
			
			List<CurrencyDetails> currencyList = currencyEvent.getCurrencyList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyDetails currencyDetails : currencyList) {
				responseBuff.append(currencyDetails.getActive());
				if (i!=currencyList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}