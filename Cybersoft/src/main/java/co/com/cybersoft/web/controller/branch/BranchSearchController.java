package co.com.cybersoft.web.controller.branch;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.branch.BranchService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.branch.BranchPageEvent;
import co.com.cybersoft.events.branch.RequestBranchPageEvent;
import co.com.cybersoft.persistence.domain.Branch;

/**
 * Search controller class for branch
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/branch/searchBranch")
public class BranchSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BranchSearchController.class);

	@Autowired
	private BranchService branchService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  branch ");
		BranchPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("branchField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("branchAscending");
			if (request.getSession().getAttribute("branchAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("branchAscending", direction);
			}
			else
				request.getSession().setAttribute("branchAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("branchField")==null){
			details = branchService.requestBranchPage(new RequestBranchPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("branchAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("branchAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("branchField"):field));
			}
			details = branchService.requestBranchPage(new RequestBranchPageEvent(pageRequest));
		}
		
		PageWrapper<Branch> page=new PageWrapper<Branch>(details.getBranchPage(),"/configuration/branch/searchBranch");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/branch/searchBranch";
	}
}