package co.com.cybersoft.maintenance.tables.web.controller.typeConceptKardex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.typeConceptKardex.TypeConceptKardexReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeConceptKardex;
import co.com.cybersoft.maintenance.tables.core.domain.TypeConceptKardexDetails;
import co.com.cybersoft.maintenance.tables.web.domain.typeConceptKardex.TypeConceptKardexFilterInfo;


@Controller
@RequestMapping("/maintenance/typeConceptKardex/export")
public class TypeConceptKardexExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeConceptKardexModificationController.class);

	@Autowired
	private TypeConceptKardexReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TypeConceptKardex to Excel");
		return reportingService.toExcel(TypeConceptKardex.class.getCanonicalName(), TypeConceptKardexDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TypeConceptKardexFilterInfo) (request.getSession().getAttribute("typeConceptKardexFilter")!=null?request.getSession().getAttribute("typeConceptKardexFilter"):new TypeConceptKardexFilterInfo()));
	}
	
}