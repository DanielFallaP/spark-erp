package co.com.cybersoft.maintenance.tables.web.controller.contract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.contract.ContractReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.maintenance.tables.core.domain.ContractDetails;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;


@Controller
@RequestMapping("/maintenance/contract/export")
public class ContractExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(ContractModificationController.class);

	@Autowired
	private ContractReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Contract to Excel");
		return reportingService.toExcel(Contract.class.getCanonicalName(), ContractDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(ContractFilterInfo) (request.getSession().getAttribute("contractFilter")!=null?request.getSession().getAttribute("contractFilter"):new ContractFilterInfo()));
	}
	
}