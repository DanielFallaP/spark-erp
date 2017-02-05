package co.com.cybersoft.maintenance.tables.web.controller.measurementUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.measurementUnit.MeasurementUnitReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.MeasurementUnit;
import co.com.cybersoft.maintenance.tables.core.domain.MeasurementUnitDetails;
import co.com.cybersoft.maintenance.tables.web.domain.measurementUnit.MeasurementUnitFilterInfo;


@Controller
@RequestMapping("/maintenance/measurementUnit/export")
public class MeasurementUnitExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(MeasurementUnitModificationController.class);

	@Autowired
	private MeasurementUnitReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting MeasurementUnit to Excel");
		return reportingService.toExcel(MeasurementUnit.class.getCanonicalName(), MeasurementUnitDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(MeasurementUnitFilterInfo) (request.getSession().getAttribute("measurementUnitFilter")!=null?request.getSession().getAttribute("measurementUnitFilter"):new MeasurementUnitFilterInfo()));
	}
	
}