package co.com.cybersoft.maintenance.tables.web.controller.jobThird;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.jobThird.JobThirdReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;
import co.com.cybersoft.maintenance.tables.web.domain.jobThird.JobThirdFilterInfo;


@Controller
@RequestMapping("/maintenance/jobThird/export")
public class JobThirdExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(JobThirdModificationController.class);

	@Autowired
	private JobThirdReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting JobThird to Excel");
		return reportingService.toExcel(JobThird.class.getCanonicalName(), JobThirdDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(JobThirdFilterInfo) (request.getSession().getAttribute("jobThirdFilter")!=null?request.getSession().getAttribute("jobThirdFilter"):new JobThirdFilterInfo()));
	}
	
}