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
public class CountrySearchByCountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/common/country/getListByCountryReturnRegion", method=RequestMethod.GET)
			public void getCountryListReturnRegion(String country, HttpServletResponse response) throws Exception{
			CountryPageEvent countryEvent = countryService.requestByContainingCountry(country);
			
			List<CountryDetails> countryList = countryEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : countryList) {
				responseBuff.append(countryDetails.getRegion());
				if (i!=countryList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByCountryReturnCountry", method=RequestMethod.GET)
			public void getCountryListReturnCountry(String country, HttpServletResponse response) throws Exception{
			CountryPageEvent countryEvent = countryService.requestByContainingCountry(country);
			
			List<CountryDetails> countryList = countryEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : countryList) {
				responseBuff.append(countryDetails.getCountry());
				if (i!=countryList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByCountryReturnLocalName", method=RequestMethod.GET)
			public void getCountryListReturnLocalName(String country, HttpServletResponse response) throws Exception{
			CountryPageEvent countryEvent = countryService.requestByContainingCountry(country);
			
			List<CountryDetails> countryList = countryEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : countryList) {
				responseBuff.append(countryDetails.getLocalName());
				if (i!=countryList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByCountryReturnFrenchName", method=RequestMethod.GET)
			public void getCountryListReturnFrenchName(String country, HttpServletResponse response) throws Exception{
			CountryPageEvent countryEvent = countryService.requestByContainingCountry(country);
			
			List<CountryDetails> countryList = countryEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : countryList) {
				responseBuff.append(countryDetails.getFrenchName());
				if (i!=countryList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByCountryReturnActive", method=RequestMethod.GET)
			public void getCountryListReturnActive(String country, HttpServletResponse response) throws Exception{
			CountryPageEvent countryEvent = countryService.requestByContainingCountry(country);
			
			List<CountryDetails> countryList = countryEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : countryList) {
				responseBuff.append(countryDetails.getActive());
				if (i!=countryList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}