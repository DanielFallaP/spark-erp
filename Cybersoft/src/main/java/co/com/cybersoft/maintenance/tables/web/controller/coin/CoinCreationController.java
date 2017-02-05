package co.com.cybersoft.maintenance.tables.web.controller.coin;

import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;
import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;
import co.com.cybersoft.maintenance.tables.core.services.coin.CoinService;
import co.com.cybersoft.maintenance.tables.events.coin.CreateCoinEvent;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinInfo;
import co.com.cybersoft.maintenance.tables.events.coin.CoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinDetailsEvent;


import co.com.cybersoft.maintenance.tables.core.services.company.CompanyService;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;


/**
 * Controller for coin
 * @author Cybersystems 2014. All rights reserved.
 *
 */
@Controller
@RequestMapping("/maintenance/coin/createCoin/{from}")
public class CoinCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(CoinCreationController.class);
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
		private CompanyService companyService;


	
	@RequestMapping(method=RequestMethod.GET)
	public String modification(Model model, HttpServletRequest request){
		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		return "/maintenance/coin/createCoin";
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String createCoin(@Valid @ModelAttribute("coinInfo") CoinInfo coinInfo, Model model, HttpServletRequest request) throws Exception{
		coinInfo.setCreated(false);
		CoinDetails coinDetails = createCoinDetails(coinInfo);
		coinDetails.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		coinDetails.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		coinDetails.setDateOfCreation(new Date());
		coinDetails.setDateOfModification(new Date());
		//coinDetails.set_companyId(((UsersDetails)request.getSession().getAttribute("_loggedInUser")).getCompanyId());

		model.addAttribute("_loggedInUser", request.getSession().getAttribute("_loggedInUser"));
		
		request.getSession().setAttribute("coinInfo", coinInfo);
		coinService.createCoin(new CreateCoinEvent(coinDetails));
		String calledFrom = coinInfo.getCalledFrom();
		coinInfo=new CoinInfo();
		coinInfo.setCreated(true);
		coinInfo.setCalledFrom(calledFrom);
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		coinInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		coinInfo.setActive(true);

		
		model.addAttribute("coinInfo", coinInfo);
		return "/maintenance/coin/createCoin";
	}
	
	private CoinDetails createCoinDetails(CoinInfo coinInfo){
		CoinDetails coinDetails = new CoinDetails();
		BeanUtils.copyProperties(coinInfo, coinDetails);
		return coinDetails;
	}
	
	@ModelAttribute("coinInfo")
	private CoinInfo getCoinInfo(@PathVariable("from") String calledFrom, HttpServletRequest request)  throws Exception {
		CoinInfo coinInfo = new CoinInfo();
		
		String value = request.getParameter("value");
		String field = request.getParameter("field");
		
		CompanyPageEvent allCompanyEvent = companyService.requestAllByCompanyName();
		coinInfo.setCompanyList(allCompanyEvent.getCompanyList());

		
		
		if (value!=null){
			RequestCoinDetailsEvent event = new RequestCoinDetailsEvent(null);
			event.setField(field);
			event.setValue(value);
			CoinDetailsEvent responseEvent = coinService.requestCoinDetails(event);
			if (responseEvent.getCoinDetails()!=null)
				BeanUtils.copyProperties(responseEvent.getCoinDetails(), coinInfo);
		}
		
		
		coinInfo.setId(null);
		coinInfo.setActive(true);

		
		coinInfo.setCalledFrom(calledFrom);
		request.getSession().setAttribute("coinInfo", coinInfo);
		
		return coinInfo;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		if (exception instanceof BindException){
			CoinInfo coinInfo=(CoinInfo) req.getSession().getAttribute("coinInfo");
			modelAndView.addObject("coinInfo", coinInfo);
			modelAndView.addObject("coinValidationException",true);
			modelAndView = setErrors(modelAndView, (BindException) exception, coinInfo);
			modelAndView.setViewName("/maintenance/coin/createCoin");
		}
		else{
			modelAndView.addObject("coinInfo", req.getSession().getAttribute("coinInfo"));
			modelAndView.addObject("coinCreateException",true);
			modelAndView.setViewName("/maintenance/coin/createCoin");
		}
		return modelAndView;
	}
	
	
	private ModelAndView setErrors(ModelAndView model, BindException exception, CoinInfo coinInfo){
		List<ObjectError> errors = exception.getAllErrors();
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (fieldError.getCodes()[0].startsWith(CyberUtils.lengthValidation)
							||fieldError.getCodes()[0].startsWith(CyberUtils.rangeValidation)){
						
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"outOfRangeErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.notEmptyValidation)  
							|| fieldError.getCodes()[0].startsWith(CyberUtils.notNullValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"requiredFieldErrorValidationMessage");
					}
					else if (fieldError.getCodes()[0].startsWith(CyberUtils.typeValidation)){
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"fieldTypeErrorValidationMessage");
						
					}
					else{
						model.addObject(fieldError.getField()+"Exception", true);
						model.addObject(fieldError.getField()+"ValidationErrorMessage","label."+"genericErrorValidationMessage");
						
					}
				}
			}
		}
		return model;
	}
}