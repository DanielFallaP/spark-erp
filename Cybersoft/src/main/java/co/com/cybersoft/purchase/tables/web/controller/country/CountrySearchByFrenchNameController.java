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
public class CountrySearchByFrenchNameController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/common/country/getListByFrenchNameReturnRegion", method=RequestMethod.GET)
			public void getCountryListReturnRegion(String frenchName, HttpServletResponse response) throws Exception{
			CountryPageEvent frenchNameEvent = countryService.requestByContainingFrenchName(frenchName);
			
			List<CountryDetails> frenchNameList = frenchNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : frenchNameList) {
				responseBuff.append(countryDetails.getRegion());
				if (i!=frenchNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByFrenchNameReturnCountry", method=RequestMethod.GET)
			public void getCountryListReturnCountry(String frenchName, HttpServletResponse response) throws Exception{
			CountryPageEvent frenchNameEvent = countryService.requestByContainingFrenchName(frenchName);
			
			List<CountryDetails> frenchNameList = frenchNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : frenchNameList) {
				responseBuff.append(countryDetails.getCountry());
				if (i!=frenchNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByFrenchNameReturnLocalName", method=RequestMethod.GET)
			public void getCountryListReturnLocalName(String frenchName, HttpServletResponse response) throws Exception{
			CountryPageEvent frenchNameEvent = countryService.requestByContainingFrenchName(frenchName);
			
			List<CountryDetails> frenchNameList = frenchNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : frenchNameList) {
				responseBuff.append(countryDetails.getLocalName());
				if (i!=frenchNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByFrenchNameReturnFrenchName", method=RequestMethod.GET)
			public void getCountryListReturnFrenchName(String frenchName, HttpServletResponse response) throws Exception{
			CountryPageEvent frenchNameEvent = countryService.requestByContainingFrenchName(frenchName);
			
			List<CountryDetails> frenchNameList = frenchNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : frenchNameList) {
				responseBuff.append(countryDetails.getFrenchName());
				if (i!=frenchNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/country/getListByFrenchNameReturnActive", method=RequestMethod.GET)
			public void getCountryListReturnActive(String frenchName, HttpServletResponse response) throws Exception{
			CountryPageEvent frenchNameEvent = countryService.requestByContainingFrenchName(frenchName);
			
			List<CountryDetails> frenchNameList = frenchNameEvent.getCountryList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (CountryDetails countryDetails : frenchNameList) {
				responseBuff.append(countryDetails.getActive());
				if (i!=frenchNameList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}