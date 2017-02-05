package co.com.cybersoft.maintenance.tables.web.controller.physicalLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.physicalLocation.PhysicalLocationReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.PhysicalLocation;
import co.com.cybersoft.maintenance.tables.core.domain.PhysicalLocationDetails;
import co.com.cybersoft.maintenance.tables.web.domain.physicalLocation.PhysicalLocationFilterInfo;


@Controller
@RequestMapping("/maintenance/physicalLocation/export")
public class PhysicalLocationExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(PhysicalLocationModificationController.class);

	@Autowired
	private PhysicalLocationReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting PhysicalLocation to Excel");
		return reportingService.toExcel(PhysicalLocation.class.getCanonicalName(), PhysicalLocationDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(PhysicalLocationFilterInfo) (request.getSession().getAttribute("physicalLocationFilter")!=null?request.getSession().getAttribute("physicalLocationFilter"):new PhysicalLocationFilterInfo()));
	}
	
}