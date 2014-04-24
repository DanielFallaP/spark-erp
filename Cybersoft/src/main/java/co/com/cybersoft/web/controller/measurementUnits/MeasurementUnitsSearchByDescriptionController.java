package co.com.cybersoft.web.controller.measurementUnits;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.MeasurementUnitsDetails;
import co.com.cybersoft.core.services.measurementUnits.MeasurementUnitsService;
import co.com.cybersoft.events.measurementUnits.MeasurementUnitsPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
@RequestMapping("/configuration/measurementUnits/getDescriptionList/{description}")
public class MeasurementUnitsSearchByDescriptionController {

	@Autowired
	private MeasurementUnitsService measurementUnitsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getMeasurementUnitsDescriptions(@PathVariable("description") String description, HttpServletResponse response) throws Exception{
		MeasurementUnitsPageEvent descriptionEvent = measurementUnitsService.requestByContainingDescription(description);
		
		List<MeasurementUnitsDetails> descriptions = descriptionEvent.getMeasurementUnitsList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (MeasurementUnitsDetails measurementUnitsDetails : descriptions) {
			responseBuff.append(measurementUnitsDetails.getDescription());
			if (i!=descriptions.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}