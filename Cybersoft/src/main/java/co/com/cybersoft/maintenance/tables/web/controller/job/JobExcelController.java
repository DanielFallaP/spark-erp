package co.com.cybersoft.maintenance.tables.web.controller.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.job.JobReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.web.domain.job.JobFilterInfo;


@Controller
@RequestMapping("/maintenance/job/export")
public class JobExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(JobModificationController.class);

	@Autowired
	private JobReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Job to Excel");
		return reportingService.toExcel(Job.class.getCanonicalName(), JobDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(JobFilterInfo) (request.getSession().getAttribute("jobFilter")!=null?request.getSession().getAttribute("jobFilter"):new JobFilterInfo()));
	}
	
}