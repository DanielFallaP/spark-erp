package co.com.cybersoft.maintenance.tables.web.controller.stateCostCenters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.stateCostCenters.StateCostCentersReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.StateCostCenters;
import co.com.cybersoft.maintenance.tables.core.domain.StateCostCentersDetails;
import co.com.cybersoft.maintenance.tables.web.domain.stateCostCenters.StateCostCentersFilterInfo;


@Controller
@RequestMapping("/maintenance/stateCostCenters/export")
public class StateCostCentersExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(StateCostCentersModificationController.class);

	@Autowired
	private StateCostCentersReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting StateCostCenters to Excel");
		return reportingService.toExcel(StateCostCenters.class.getCanonicalName(), StateCostCentersDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(StateCostCentersFilterInfo) (request.getSession().getAttribute("stateCostCentersFilter")!=null?request.getSession().getAttribute("stateCostCentersFilter"):new StateCostCentersFilterInfo()));
	}
	
}