package co.com.cybersoft.docsTest.web.controller.requisition;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/deleteRequisition")
public class RequisitionDeletionController {

	@RequestMapping(method=RequestMethod.GET)
	public void delete(HttpServletResponse response) throws Exception{
		response.getWriter().append("ok");
		response.getWriter().close();
	}
}
