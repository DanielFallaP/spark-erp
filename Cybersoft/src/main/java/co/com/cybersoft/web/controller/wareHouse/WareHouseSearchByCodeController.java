package co.com.cybersoft.web.controller.wareHouse;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.domain.WareHouseDetails;
import co.com.cybersoft.core.services.wareHouse.WareHouseService;
import co.com.cybersoft.events.wareHouse.WareHousePageEvent;
import co.com.cybersoft.util.CyberUtils;

/**
 * 
 * @author daniel
 *
 */
@Controller
@RequestMapping("/configuration/wareHouse/getCodeList/{code}")
public class WareHouseSearchByCodeController {

	@Autowired
	private WareHouseService wareHouseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public void getWareHouseCodes(@PathVariable("code") String code, HttpServletResponse response) throws Exception{
		WareHousePageEvent codesEvent = wareHouseService.requestByCodePrefix(code);
		
		List<WareHouseDetails> codes = codesEvent.getWareHouseList();
		StringBuffer responseBuff=new StringBuffer();
		int i=1;
		for (WareHouseDetails wareHouseDetails : codes) {
			responseBuff.append(wareHouseDetails.getCode());
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