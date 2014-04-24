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

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/measurementUnits/getCodeList/{code}")
public class MeasurementUnitsSearchByCodeController {

	@Autowired
	private MeasurementUnitsService measurementUnitsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getMeasurementUnitsCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		MeasurementUnitsPageEvent codesEvent = measurementUnitsService.requestByCodePrefix(code);
		
		List<MeasurementUnitsDetails> codes = codesEvent.getMeasurementUnitsList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (MeasurementUnitsDetails measurementUnitsDetails : codes) {
			responseBuff.append(measurementUnitsDetails.getCode());
			if (i!=codes.size()){
				responseBuff.append(CyberUtils.arraySeparator);
			}
			i++;
		}
		PrintWriter writer = response.getWriter();
		writer.append(responseBuff.toString());
		writer.close();
	}
}