package co.com.cybersoft.maintenance.tables.web.controller.costingType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.costingType.CostingTypeReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.CostingType;
import co.com.cybersoft.maintenance.tables.core.domain.CostingTypeDetails;
import co.com.cybersoft.maintenance.tables.web.domain.costingType.CostingTypeFilterInfo;


@Controller
@RequestMapping("/maintenance/costingType/export")
public class CostingTypeExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CostingTypeModificationController.class);

	@Autowired
	private CostingTypeReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting CostingType to Excel");
		return reportingService.toExcel(CostingType.class.getCanonicalName(), CostingTypeDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CostingTypeFilterInfo) (request.getSession().getAttribute("costingTypeFilter")!=null?request.getSession().getAttribute("costingTypeFilter"):new CostingTypeFilterInfo()));
	}
	
}