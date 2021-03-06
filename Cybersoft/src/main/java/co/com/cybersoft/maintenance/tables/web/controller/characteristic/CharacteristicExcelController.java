package co.com.cybersoft.maintenance.tables.web.controller.characteristic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.characteristic.CharacteristicReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Characteristic;
import co.com.cybersoft.maintenance.tables.core.domain.CharacteristicDetails;
import co.com.cybersoft.maintenance.tables.web.domain.characteristic.CharacteristicFilterInfo;


@Controller
@RequestMapping("/maintenance/characteristic/export")
public class CharacteristicExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(CharacteristicModificationController.class);

	@Autowired
	private CharacteristicReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Characteristic to Excel");
		return reportingService.toExcel(Characteristic.class.getCanonicalName(), CharacteristicDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(CharacteristicFilterInfo) (request.getSession().getAttribute("characteristicFilter")!=null?request.getSession().getAttribute("characteristicFilter"):new CharacteristicFilterInfo()));
	}
	
}