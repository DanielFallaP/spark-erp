package co.com.cybersoft.maintenance.tables.web.controller.conceptKardex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.conceptKardex.ConceptKardexReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.ConceptKardex;
import co.com.cybersoft.maintenance.tables.core.domain.ConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.web.domain.conceptKardex.ConceptKardexFilterInfo;


@Controller
@RequestMapping("/maintenance/conceptKardex/export")
public class ConceptKardexExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ConceptKardexModificationController.class);

	@Autowired
	private ConceptKardexReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting ConceptKardex to Excel");
		return reportingService.toExcel(ConceptKardex.class.getCanonicalName(), ConceptKardexDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ConceptKardexFilterInfo) (request.getSession().getAttribute("conceptKardexFilter")!=null?request.getSession().getAttribute("conceptKardexFilter"):new ConceptKardexFilterInfo()));
	}
	
}