package co.com.cybersoft.purchase.tables.web.controller.region;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.region.RegionReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Region;
import co.com.cybersoft.purchase.tables.core.domain.RegionDetails;
import co.com.cybersoft.purchase.tables.web.domain.region.RegionFilterInfo;


@Controller
@RequestMapping("/purchase/region/export")
public class RegionExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(RegionModificationController.class);

	@Autowired
	private RegionReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Region to Excel");
		return reportingService.toExcel(Region.class.getCanonicalName(), RegionDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(RegionFilterInfo) (request.getSession().getAttribute("regionFilter")!=null?request.getSession().getAttribute("regionFilter"):new RegionFilterInfo()));
	}
	
}