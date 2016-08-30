package co.com.cybersoft.purchase.tables.web.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import co.com.cybersoft.purchase.tables.core.services.users.UsersReportingService;

import co.com.cybersoft.man.ExcelReportResponse;
import co.com.cybersoft.man.services.excel.ReportingService;
import co.com.cybersoft.purchase.tables.persistence.domain.Users;
import co.com.cybersoft.purchase.tables.core.domain.UsersDetails;
import co.com.cybersoft.purchase.tables.web.domain.users.UsersFilterInfo;


@Controller
@RequestMapping("/purchase/users/export")
public class UsersExcelController {
	private static final Logger LOG=LoggerFactory.getLogger(UsersModificationController.class);

	@Autowired
	private UsersReportingService reportingService;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ExcelReportResponse toExcel(HttpServletRequest request) throws Exception{
		LOG.debug("Exporting Users to Excel");
		return reportingService.toExcel(Users.class.getCanonicalName(), UsersDetails.class.getCanonicalName(), LocaleContextHolder.getLocale(),(UsersFilterInfo) (request.getSession().getAttribute("usersFilter")!=null?request.getSession().getAttribute("usersFilter"):new UsersFilterInfo()));
	}
	
}