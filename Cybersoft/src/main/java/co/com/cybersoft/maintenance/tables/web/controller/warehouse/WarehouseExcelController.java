package co.com.cybersoft.maintenance.tables.web.controller.warehouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.maintenance.tables.core.services.warehouse.WarehouseReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.maintenance.tables.persistence.domain.Warehouse;
import co.com.cybersoft.maintenance.tables.core.domain.WarehouseDetails;
import co.com.cybersoft.maintenance.tables.web.domain.warehouse.WarehouseFilterInfo;


@Controller
@RequestMapping("/maintenance/warehouse/export")
public class WarehouseExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(WarehouseModificationController.class);

	@Autowired
	private WarehouseReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Warehouse to Excel");
		return reportingService.toExcel(Warehouse.class.getCanonicalName(), WarehouseDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(WarehouseFilterInfo) (request.getSession().getAttribute("warehouseFilter")!=null?request.getSession().getAttribute("warehouseFilter"):new WarehouseFilterInfo()));
	}
	
}