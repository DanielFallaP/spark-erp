package co.com.cybersoft.maintenance.tables.web.controller.costCentersCustomers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.costCentersCustomers.CostCentersCustomersReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostCentersCustomers;
import co.com.cybersoft.maintenance.tables.core.domain.CostCentersCustomersDetails;
import co.com.cybersoft.maintenance.tables.web.domain.costCentersCustomers.CostCentersCustomersFilterInfo;


@Controller
@RequestMapping("/maintenance/costCentersCustomers/export")
public class CostCentersCustomersExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CostCentersCustomersModificationController.class);

	@Autowired
	private CostCentersCustomersReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting CostCentersCustomers to Excel");
		return reportingService.toExcel(CostCentersCustomers.class.getCanonicalName(), CostCentersCustomersDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CostCentersCustomersFilterInfo) (request.getSession().getAttribute("costCentersCustomersFilter")!=null?request.getSession().getAttribute("costCentersCustomersFilter"):new CostCentersCustomersFilterInfo()));
	}
	
}