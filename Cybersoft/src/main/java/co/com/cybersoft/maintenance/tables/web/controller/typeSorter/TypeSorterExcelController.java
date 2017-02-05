package co.com.cybersoft.maintenance.tables.web.controller.typeSorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.typeSorter.TypeSorterReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.TypeSorter;
import co.com.cybersoft.maintenance.tables.core.domain.TypeSorterDetails;
import co.com.cybersoft.maintenance.tables.web.domain.typeSorter.TypeSorterFilterInfo;


@Controller
@RequestMapping("/maintenance/typeSorter/export")
public class TypeSorterExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(TypeSorterModificationController.class);

	@Autowired
	private TypeSorterReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting TypeSorter to Excel");
		return reportingService.toExcel(TypeSorter.class.getCanonicalName(), TypeSorterDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(TypeSorterFilterInfo) (request.getSession().getAttribute("typeSorterFilter")!=null?request.getSession().getAttribute("typeSorterFilter"):new TypeSorterFilterInfo()));
	}
	
}