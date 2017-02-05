package co.com.cybersoft.maintenance.tables.web.controller.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.state.StateReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.State;
import co.com.cybersoft.maintenance.tables.core.domain.StateDetails;
import co.com.cybersoft.maintenance.tables.web.domain.state.StateFilterInfo;


@Controller
@RequestMapping("/maintenance/state/export")
public class StateExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(StateModificationController.class);

	@Autowired
	private StateReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting State to Excel");
		return reportingService.toExcel(State.class.getCanonicalName(), StateDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(StateFilterInfo) (request.getSession().getAttribute("stateFilter")!=null?request.getSession().getAttribute("stateFilter"):new StateFilterInfo()));
	}
	
}