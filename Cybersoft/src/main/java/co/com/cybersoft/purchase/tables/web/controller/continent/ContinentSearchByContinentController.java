package co.com.cybersoft.purchase.tables.web.controller.continent;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.purchase.tables.core.domain.ContinentDetails;
import co.com.cybersoft.purchase.tables.core.services.continent.ContinentService;
import co.com.cybersoft.purchase.tables.events.continent.ContinentPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class ContinentSearchByContinentController {

	@Autowired
	private ContinentService continentService;
	
	@RequestMapping(value="/common/continent/getListByContinentReturnContinent", method=RequestMethod.GET)
			public void getContinentListReturnContinent(String continent, HttpServletResponse response) throws Exception{
			ContinentPageEvent continentEvent = continentService.requestByContainingContinent(continent);
			
			List<ContinentDetails> continentList = continentEvent.getContinentList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (ContinentDetails continentDetails : continentList) {
				responseBuff.append(continentDetails.getContinent());
				if (i!=continentList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/continent/getListByContinentReturnActive", method=RequestMethod.GET)
			public void getContinentListReturnActive(String continent, HttpServletResponse response) throws Exception{
			ContinentPageEvent continentEvent = continentService.requestByContainingContinent(continent);
			
			List<ContinentDetails> continentList = continentEvent.getContinentList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (ContinentDetails continentDetails : continentList) {
				responseBuff.append(continentDetails.getActive());
				if (i!=continentList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}