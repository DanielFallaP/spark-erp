package co.com.cybersoft.maintenance.tables.web.controller.effectFail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.effectFail.EffectFailReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.EffectFail;
import co.com.cybersoft.maintenance.tables.core.domain.EffectFailDetails;
import co.com.cybersoft.maintenance.tables.web.domain.effectFail.EffectFailFilterInfo;


@Controller
@RequestMapping("/maintenance/effectFail/export")
public class EffectFailExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(EffectFailModificationController.class);

	@Autowired
	private EffectFailReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting EffectFail to Excel");
		return reportingService.toExcel(EffectFail.class.getCanonicalName(), EffectFailDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(EffectFailFilterInfo) (request.getSession().getAttribute("effectFailFilter")!=null?request.getSession().getAttribute("effectFailFilter"):new EffectFailFilterInfo()));
	}
	
}