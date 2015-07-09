package co.com.cybersoft.purchase.tables.web.controller.region;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.core.services.region.RegionService;
import co.com.cybersoft.purchase.tables.events.region.RegionPageEvent;
import co.com.cybersoft.util.CyberUtils;

@Controller
public class RegionSearchByRegionController {

	@Autowired
	private RegionService regionService;
	
	@RequestMapping(value="/common/region/getListByRegionReturnContinent", method=RequestMethod.GET)
			public void getRegionListReturnContinent(String region, HttpServletResponse response) throws Exception{
			RegionPageEvent regionEvent = regionService.requestByContainingRegion(region);
			
			List<RegionDetails> regionList = regionEvent.getRegionList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (RegionDetails regionDetails : regionList) {
				responseBuff.append(regionDetails.getContinent());
				if (i!=regionList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/region/getListByRegionReturnRegion", method=RequestMethod.GET)
			public void getRegionListReturnRegion(String region, HttpServletResponse response) throws Exception{
			RegionPageEvent regionEvent = regionService.requestByContainingRegion(region);
			
			List<RegionDetails> regionList = regionEvent.getRegionList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (RegionDetails regionDetails : regionList) {
				responseBuff.append(regionDetails.getRegion());
				if (i!=regionList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}
	@RequestMapping(value="/common/region/getListByRegionReturnActive", method=RequestMethod.GET)
			public void getRegionListReturnActive(String region, HttpServletResponse response) throws Exception{
			RegionPageEvent regionEvent = regionService.requestByContainingRegion(region);
			
			List<RegionDetails> regionList = regionEvent.getRegionList();
			StringBuffer responseBuff=new StringBuffer();
			int i=1;
			for (RegionDetails regionDetails : regionList) {
				responseBuff.append(regionDetails.getActive());
				if (i!=regionList.size()){
					responseBuff.append(CyberUtils.arraySeparator);
				}
				i++;
			}
			
			PrintWriter writer = response.getWriter();
			writer.append(responseBuff.toString());
			writer.close();
		}

}