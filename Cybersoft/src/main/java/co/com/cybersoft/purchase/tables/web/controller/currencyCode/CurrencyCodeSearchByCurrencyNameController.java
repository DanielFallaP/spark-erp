package co.com.cybersoft.purchase.tables.web.controller.currencyCode;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.core.services.currencyCode.CurrencyCodeService;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class CurrencyCodeSearchByCurrencyNameController {

	@Autowired
	private CurrencyCodeService currencyCodeService;
	
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnCurrencyName", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnCurrencyName(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getCurrencyName());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnCountry", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnCountry(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getCountry());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnCurrency", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnCurrency(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getCurrency());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnSymbol", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnSymbol(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getSymbol());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnDec1", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnDec1(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getDec1());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnDec2", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnDec2(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getDec2());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnDec3", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnDec3(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getDec3());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnHex1", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnHex1(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getHex1());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnHex2", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnHex2(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getHex2());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnHex3", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnHex3(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getHex3());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/currencyCode/getListByCurrencyNameReturnActive", method=RequestMethod.GET)
			public void getCurrencyCodeListReturnActive(String currencyName, HttpServletResponse response) throws Exception{
			CurrencyCodePageEvent currencyNameEvent = currencyCodeService.requestByContainingCurrencyName(currencyName);
			
			List<CurrencyCodeDetails> currencyNameList = currencyNameEvent.getCurrencyCodeList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CurrencyCodeDetails currencyCodeDetails : currencyNameList) {
				responseBuff.append(currencyCodeDetails.getActive());
				if (i!=currencyNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}