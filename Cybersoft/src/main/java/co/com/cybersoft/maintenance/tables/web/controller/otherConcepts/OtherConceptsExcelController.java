package co.com.cybersoft.maintenance.tables.web.controller.otherConcepts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.otherConcepts.OtherConceptsReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.OtherConcepts;
import co.com.cybersoft.maintenance.tables.core.domain.OtherConceptsDetails;
import co.com.cybersoft.maintenance.tables.web.domain.otherConcepts.OtherConceptsFilterInfo;


@Controller
@RequestMapping("/maintenance/otherConcepts/export")
public class OtherConceptsExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(OtherConceptsModificationController.class);

	@Autowired
	private OtherConceptsReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting OtherConcepts to Excel");
		return reportingService.toExcel(OtherConcepts.class.getCanonicalName(), OtherConceptsDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(OtherConceptsFilterInfo) (request.getSession().getAttribute("otherConceptsFilter")!=null?request.getSession().getAttribute("otherConceptsFilter"):new OtherConceptsFilterInfo()));
	}
	
}