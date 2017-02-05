package co.com.cybersoft.maintenance.tables.web.controller.sorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.sorter.SorterReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Sorter;
import co.com.cybersoft.maintenance.tables.core.domain.SorterDetails;
import co.com.cybersoft.maintenance.tables.web.domain.sorter.SorterFilterInfo;


@Controller
@RequestMapping("/maintenance/sorter/export")
public class SorterExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(SorterModificationController.class);

	@Autowired
	private SorterReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Sorter to Excel");
		return reportingService.toExcel(Sorter.class.getCanonicalName(), SorterDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(SorterFilterInfo) (request.getSession().getAttribute("sorterFilter")!=null?request.getSession().getAttribute("sorterFilter"):new SorterFilterInfo()));
	}
	
}