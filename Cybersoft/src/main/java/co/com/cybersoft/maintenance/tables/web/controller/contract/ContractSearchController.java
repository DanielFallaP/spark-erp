package co.com.cybersoft.maintenance.tables.web.controller.contract;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.maintenance.tables.core.services.contract.ContractService;
import co.com.cybersoft.util.PageWrapper;
import co.com.cybersoft.maintenance.tables.events.contract.ContractPageEvent;
import co.com.cybersoft.maintenance.tables.events.contract.RequestContractPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Contract;
import co.com.cybersoft.maintenance.tables.web.domain.contract.ContractFilterInfo;

/**
 * Search controller class for contract
 * @author Cybersystems 2016. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/contract/searchContract")
public class ContractSearchController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContractSearchController.class);

	@Autowired
	private ContractService contractService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(Model model, Pageable pageable, String field, HttpServletRequest request) throws Exception{
		ContractFilterInfo f=(ContractFilterInfo) request.getSession().getAttribute("contractFilter");
		if (f!=null){
			f.getContractFilterList().clear();
		}
		
		LOG.debug("Retrieving  contract ");
		ContractPageEvent details;
		Boolean direction=null;
		if (field!=null){
			direction=(Boolean) request.getSession().getAttribute("contractAscending");
			String oldField=(String)request.getSession().getAttribute("contractField");
			if (oldField!=null && request.getSession().getAttribute("contractAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("contractAscending", direction);
			}
			else
				request.getSession().setAttribute("contractAscending", true);
			request.getSession().setAttribute("contractField", field);
		}
		PageRequest pageRequest=null;
		if (field==null && request.getSession().getAttribute("contractField")==null){
			pageRequest = new PageRequest(pageable.getPageNumber(),pageable.getPageSize(), Direction.DESC, "id");
			details = contractService.requestContractPage(new RequestContractPageEvent(pageRequest));
		}
		else{
			if (request.getSession().getAttribute("contractAscending")!=null){
								
				direction=(Boolean) request.getSession().getAttribute("contractAscending");
				pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) (field==null?request.getSession().getAttribute("contractField"):field));
			}
			details = contractService.requestContractPage(new RequestContractPageEvent(pageRequest));
		}
		
		PageWrapper<Contract> page=new PageWrapper<Contract>(details.getContractPage(),"/maintenance/contract/searchContract");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("contractField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", page.getTotalElements());
		model.addAttribute("ffilterAsText", "All");
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		return "/maintenance/contract/searchContract";
	}
	
	private Boolean hasActions(ContractFilterInfo filter){
		return false;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String filter(Model model, String field, Pageable pageable, @ModelAttribute("contractFilterInfo")ContractFilterInfo filter, HttpServletRequest request) throws Exception{
		ContractFilterInfo f=(ContractFilterInfo) request.getSession().getAttribute("contractFilter");
		if (f!=null && f.getContractFilterList().size()!=0)
			filter.getContractFilterList().addAll(f.getContractFilterList());
		if (filter.getAaddFilter())
			filter.getContractFilterList().add((ContractFilterInfo) (request.getSession().getAttribute("contractFilterCopy")!=null?request.getSession().getAttribute("contractFilterCopy"):new ContractFilterInfo()));
		
		Boolean direction=null;
		if (filter.getSelectedFilterField()!=null && !filter.getSelectedFilterField().equals("")){
			direction=(Boolean) request.getSession().getAttribute("contractAscending");
			String oldField=(String)request.getSession().getAttribute("contractField");
			if (oldField!=null && oldField.equals(filter.getSelectedFilterField()) && filter.getChangeSortingFieldDirection() && request.getSession().getAttribute("contractAscending")!=null){
				direction=!direction;
				request.getSession().setAttribute("contractAscending", direction);
			}
			else if (request.getSession().getAttribute("contractAscending")==null)
				request.getSession().setAttribute("contractAscending", true);
			request.getSession().setAttribute("contractField", filter.getSelectedFilterField());
		}
		
		RequestContractPageEvent pageEvent=null;
		PageRequest pageRequest=null;
		if ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))&& request.getSession().getAttribute("contractField")==null){
			pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), Direction.DESC,"id");
			pageEvent = new RequestContractPageEvent(pageRequest,filter);
		}
		else {
			if (request.getSession().getAttribute("contractAscending")!=null){
				direction=(Boolean) request.getSession().getAttribute("contractAscending");
				pageRequest = new PageRequest(filter.getSelectedFilterPage()-1, pageable.getPageSize(), direction?Direction.ASC:Direction.DESC, (String) ((filter.getSelectedFilterField()==null || filter.getSelectedFilterField().equals(""))?request.getSession().getAttribute("contractField"):filter.getSelectedFilterField()));
				pageEvent = new RequestContractPageEvent(pageRequest,filter);			}
		}
		
		ContractPageEvent details = contractService.requestContractFilterPage(pageEvent);
		PageWrapper<Contract> page=new PageWrapper<Contract>(details.getContractPage(),"/maintenance/contract/searchContract");
		model.addAttribute("page", page);
		model.addAttribute("list",page.getContent());
		model.addAttribute("_field", request.getSession().getAttribute("contractField"));
		model.addAttribute("_direction", direction);
		model.addAttribute("_totalCount", details.getTotalCount());
		model.addAttribute("ffilterAsText", filter.getFfilterAsText());
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		if (hasActions(filter)){
			filter.setAaaaction(null);
	    }

	    request.getSession().setAttribute("contractFilter", filter);
    	request.getSession().setAttribute("contractFilterCopy", filter);
    	filter.setAaddFilter(Boolean.FALSE);
		
		return "/maintenance/contract/searchContract";
	}
	
	@ModelAttribute("contractFilterInfo")
	private ContractFilterInfo getContractFilterInfo(){
		return new ContractFilterInfo();
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
}