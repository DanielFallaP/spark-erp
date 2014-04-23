package co.com.cybersoft.web.controller.contract;

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

import co.com.cybersoft.core.services.contract.ContractService;
import co.com.cybersoft.core.util.PageWrapper;
import co.com.cybersoft.events.contract.ContractPageEvent;
import co.com.cybersoft.events.contract.RequestContractPageEvent;
import co.com.cybersoft.persistence.domain.Contract;

/**
 * Search controller class for contract
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/configuration/contract/searchContract")
public class ContractSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContractSearchController.class);

	@Autowired
	private ContractService contractService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		LOG.debug("Retrieving  contract ");
		ContractPageEvent details;
		if (field!=null){
			request.getSession().setAttribute("contractField", field);
			Boolean direction=(Boolean) request.getSession().getAttribute("contractAscending");
			if (request.getSession().getAttribute("contractAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("contractAscending", direction);
			}
			else
				request.getSession().setAttribute("contractAscending", true);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("contractField")==null){
			details = contractService.requestContractPage(new RequestContractPageEvent(pageable));
		}
		else{
			boolean direction;
			if (request.getSession().getAttribute("contractAscending")!=null){
				
				direction=(boolean) request.getSession().getAttribute("contractAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("contractField"):field));
			}
			details = contractService.requestContractPage(new RequestContractPageEvent(pageRequest));
		}
		
		PageWrapper<Contract> page=new PageWrapper<Contract>(details.getContractPage(),"/configuration/contract/searchContract");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		return "/configuration/contract/searchContract";
	}
}