package co.com.cybersoft.purchase.tables.web.controller.country;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.core.services.country.CountryService;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class CountrySearchByLocalNameController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/common/country/getListByLocalNameReturnRegion", method=RequestMethod.GET)
			public void getCountryListReturnRegion(String localName, HttpServletResponse response) throws Exception{
			CountryPageEvent localNameEvent = countryService.requestByContainingLocalName(localName);
			
			List<CountryDetails> localNameList = localNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : localNameList) {
				responseBuff.append(countryDetails.getRegion());
				if (i!=localNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByLocalNameReturnCountry", method=RequestMethod.GET)
			public void getCountryListReturnCountry(String localName, HttpServletResponse response) throws Exception{
			CountryPageEvent localNameEvent = countryService.requestByContainingLocalName(localName);
			
			List<CountryDetails> localNameList = localNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : localNameList) {
				responseBuff.append(countryDetails.getCountry());
				if (i!=localNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByLocalNameReturnLocalName", method=RequestMethod.GET)
			public void getCountryListReturnLocalName(String localName, HttpServletResponse response) throws Exception{
			CountryPageEvent localNameEvent = countryService.requestByContainingLocalName(localName);
			
			List<CountryDetails> localNameList = localNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : localNameList) {
				responseBuff.append(countryDetails.getLocalName());
				if (i!=localNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByLocalNameReturnFrenchName", method=RequestMethod.GET)
			public void getCountryListReturnFrenchName(String localName, HttpServletResponse response) throws Exception{
			CountryPageEvent localNameEvent = countryService.requestByContainingLocalName(localName);
			
			List<CountryDetails> localNameList = localNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : localNameList) {
				responseBuff.append(countryDetails.getFrenchName());
				if (i!=localNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByLocalNameReturnActive", method=RequestMethod.GET)
			public void getCountryListReturnActive(String localName, HttpServletResponse response) throws Exception{
			CountryPageEvent localNameEvent = countryService.requestByContainingLocalName(localName);
			
			List<CountryDetails> localNameList = localNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : localNameList) {
				responseBuff.append(countryDetails.getActive());
				if (i!=localNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}