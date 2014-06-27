package co.com.cybersoft.docs.web.controller.requisition;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import co.com.cybersoft.tables.core.services.country.CountryService;
import co.com.cybersoft.tables.events.country.CountryPageEvent;
import co.com.cybersoft.tables.core.services.state.StateService;
import co.com.cybersoft.tables.events.state.StatePageEvent;
import co.com.cybersoft.tables.core.services.populatedPlace.PopulatedPlaceService;
import co.com.cybersoft.docs.web.domain.requisition.RequisitionInfo;
import co.com.cybersoft.tables.events.populatedPlace.PopulatedPlacePageEvent;



/**
 * Controller for requisition
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/docs/requisition/retrieveCity")
public class RequisitionCityController {

	@Autowired
		private CountryService countryService;

	@Autowired
		private StateService stateService;

	@Autowired
		private PopulatedPlaceService populatedPlaceService;


	
	@RequestMapping(value="/retrieve", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RequisitionInfo retrieveLists(HttpServletRequest request) throws Exception{
		RequisitionInfo requisition = new RequisitionInfo();
		
		String country = request.getParameter("country");
		String state = request.getParameter("state");

		
		CountryPageEvent allCountryPageEvent = null;
		StatePageEvent allStatePageEvent = null;
		PopulatedPlacePageEvent allPopulatedPlacePageEvent = null;


		if (country!=null || (allCountryPageEvent!=null && !allCountryPageEvent.getCountryList().isEmpty())){
			allStatePageEvent = stateService.requestAllByCountryName(country==null?allCountryPageEvent.getCountryList().get(0).getCountry():country);
			requisition.setStateList(allStatePageEvent.getStateList());
		}
		if (state!=null || (allStatePageEvent!=null && !allStatePageEvent.getStateList().isEmpty())){
			allPopulatedPlacePageEvent = populatedPlaceService.requestAllByStateName(state==null?allStatePageEvent.getStateList().get(0).getState():state);
			requisition.setPopulatedPlaceList(allPopulatedPlacePageEvent.getPopulatedPlaceList());
		}


		return requisition;
	}
}