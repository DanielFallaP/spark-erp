package co.com.cybersoft.maintenance.tables.web.controller.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.company.CompanyReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.web.domain.company.CompanyFilterInfo;


@Controller
@RequestMapping("/maintenance/company/export")
public class CompanyExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CompanyModificationController.class);

	@Autowired
	private CompanyReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Company to Excel");
		return reportingService.toExcel(Company.class.getCanonicalName(), CompanyDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CompanyFilterInfo) (request.getSession().getAttribute("companyFilter")!=null?request.getSession().getAttribute("companyFilter"):new CompanyFilterInfo()));
	}
	
}