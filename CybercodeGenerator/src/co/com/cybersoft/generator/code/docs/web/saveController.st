package co.com.cybersoft.$module$.docs.web.controller.$docName$;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.com.cybersoft.$module$.docs.events.$docName$.Request$upperDocName$Event;
import co.com.cybersoft.$module$.docs.events.$docName$.$upperDocName$Event;
import co.com.cybersoft.$module$.docs.events.$docName$.Save$upperDocName$Event;
import co.com.cybersoft.$module$.docs.persistence.services.$docName$.$upperDocName$PersistenceService;
import co.com.cybersoft.$module$.docs.web.domain.$docName$.$upperDocName$Info;
import co.com.cybersoft.$module$.docs.web.domain.$docName$.$upperDocName$BodyInfo;
import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.SparkBindingException;
import org.springframework.security.core.context.SecurityContextHolder;
$imports$


@Controller
@RequestMapping("/docs/$docName$")
public class $upperDocName$Controller {
	
	@Autowired
	private $upperDocName$PersistenceService $docName$Service;
	
	$fields$
	
	@Autowired
	@Qualifier("jsr303Validator") Validator validator;
	
	private static final Logger LOG = LoggerFactory.getLogger($upperDocName$Controller.class);
	
	@RequestMapping(method=RequestMethod.POST)
	public String save$upperDocName$(@ModelAttribute("$docName$Info") $upperDocName$Info $docName$Info, @ModelAttribute("$docName$BodyInfo") $upperDocName$BodyInfo current, @ModelAttribute("$docName$BodyModificationInfo") $upperDocName$BodyInfo modified,Model model, HttpServletRequest request) throws Exception{
		$upperDocName$Event $docName$Event=null;
		if (modified.get$upperDocName$BodyModificationInfo().getSubmit().equals("modification")){
			modificationManualValidation(modified.get$upperDocName$BodyModificationInfo(), "$docName$BodyModificationInfo", CyberUtils.modificationForm, $docName$Info);
			
			$docName$Info.set$upperDocName$BodyModificationInfo(modified.get$upperDocName$BodyModificationInfo());
			$onBodyPreMod$
			$rowModificationOperations$
			$docName$Event = $docName$Service.update$upperDocName$Body(new Save$upperDocName$Event($docName$Info));
			$onBodyMod$
		}
		else if (modified.get$upperDocName$BodyModificationInfo().getSubmit().equals("creation")){
			manualValidation(current, "$docName$BodyInfo", CyberUtils.additionForm, $docName$Info);
			
			$docName$Info.setCurrent$upperDocName$BodyInfo(current);
			$onBodyPreAdd$
			$rowAdditionOperations$			
			$docName$Event = $docName$Service.create$upperDocName$BodyRecord(new Save$upperDocName$Event($docName$Info));
			$onBodyAdd$
			$upperDocName$BodyInfo $docName$BodyInfo = get$upperDocName$BodyInfo(request);
			model.addAttribute("$docName$BodyInfo", $docName$BodyInfo);
		}
		else if (modified.get$upperDocName$BodyModificationInfo().getSubmit().endsWith("deletion")){
			List<String> toDelete = Arrays.asList($docName$Info.getDeletion().split(","));
			$docName$Event=$docName$Service.delete$upperDocName$BodyRecords(new Save$upperDocName$Event($docName$Info), toDelete);			
		}
		else{
			manualValidation($docName$Info, "$docName$Info", "", $docName$Info);
			
			$onHeaderPreSave$
			$docName$Event = $docName$Service.save$upperDocName$(new Save$upperDocName$Event($docName$Info));
			$onHeaderSave$	
		}
		
		if ($docName$Info.getCreated()!=null && !$docName$Info.getCreated()){
			return "redirect:/docs/$docName$?id="+$docName$Event.get$upperDocName$().getId();
		}
		else{
			
			$docName$Info.setShowBody(true);
			$docName$Info.set_toSave(true);
			$docName$Info.setId($docName$Event.get$upperDocName$().getId());
			$docName$Info.set$upperDocName$BodyList($docName$Event.get$upperDocName$().get$upperDocName$BodyList());
			$docName$Info.setNumericId($docName$Event.get$upperDocName$().getNumericId());
			$docName$Info.setDateOfCreation($docName$Event.get$upperDocName$().getDateOfCreation());
			$docName$Info.setCreatedBy($docName$Event.get$upperDocName$().getCreatedBy());
			
			$compoundLists$
			
			model.addAttribute("$docName$Info",$docName$Info);
			model.addAttribute("$docName$BodyList", $docName$Info.get$upperDocName$BodyList());
			return "/docs/$docName$/save$upperDocName$";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String search(){
		return "/docs/$docName$/save$upperDocName$";
	}
	
	private void modificationManualValidation($upperDocName$BodyInfo object, String objectName, String origin, $upperDocName$Info $docName$) throws Exception{
		HashMap<Object, Object> errorMap = new HashMap<>();
		MapBindingResult mapBindingResult = new MapBindingResult(errorMap, objectName);
		validator.validate(object, mapBindingResult);
		
		if ($docName$.get$upperDocName$BodyList().indexOf(object)!=$docName$.get$upperDocName$BodyList().lastIndexOf(object)){
			errorMap.put("error", "duplicateError");
			throw new SparkBindingException(mapBindingResult.getAllErrors(), origin, "");
		}
					
		if (!mapBindingResult.getAllErrors().isEmpty()){
			throw new SparkBindingException(mapBindingResult.getAllErrors(), origin,object.getId());
		}
		
	}
	
	private void manualValidation(Object object, String objectName, String origin, $upperDocName$Info $docName$) throws Exception{
		HashMap<Object, Object> errorMap = new HashMap<>();
		MapBindingResult mapBindingResult = new MapBindingResult(errorMap, objectName);
		validator.validate(object, mapBindingResult);
		
		if (object instanceof $upperDocName$BodyInfo){
			if ($docName$.get$upperDocName$BodyList().contains(object)){
				errorMap.put("error", "duplicateError");
				throw new SparkBindingException(mapBindingResult.getAllErrors(), origin, "");
			}
		}
					
		if (!mapBindingResult.getAllErrors().isEmpty()){
			throw new SparkBindingException(mapBindingResult.getAllErrors(), origin, "");
		}
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView constraintError(HttpServletRequest req, Exception exception){
		ModelAndView modelAndView = new ModelAndView();
		try {
		exception.printStackTrace();
		$upperDocName$Info $docName$Info=($upperDocName$Info) req.getSession().getAttribute("$docName$Info");
		$upperDocName$BodyInfo $docName$BodyInfo;
		$upperDocName$BodyInfo $docName$BodyModificationInfo;
		if (exception instanceof BindException){
			$docName$BodyInfo = get$upperDocName$BodyInfo(req);
			$docName$BodyModificationInfo = get$upperDocName$BodyModificationInfo(req);
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$ValidationException",true);
			modelAndView = setErrors(modelAndView, ((BindException) exception).getAllErrors(), $docName$Info, "", "");
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		else if (exception instanceof SparkBindingException){
			$docName$BodyInfo = ((SparkBindingException) exception).getModifiedId().equals("")?$docName$Info.getCurrent$upperDocName$BodyInfo():get$upperDocName$BodyInfo(req);
			$docName$BodyModificationInfo = ((SparkBindingException) exception).getModifiedId().equals("")?get$upperDocName$BodyModificationInfo(req):$docName$Info.get$upperDocName$BodyModificationInfo();
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$ValidationException",true);
			modelAndView = setErrors(modelAndView, ((SparkBindingException) exception).getErrors(), $docName$Info, ((SparkBindingException) exception).getOrigin(), ((SparkBindingException) exception).getModifiedId());
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		else {
			$docName$BodyInfo = get$upperDocName$BodyInfo(req);
			$docName$BodyModificationInfo = get$upperDocName$BodyModificationInfo(req);
			modelAndView.addObject("$docName$Info", $docName$Info);
			modelAndView.addObject("$docName$CreateException",true);
			modelAndView.setViewName("/docs/$docName$/save$upperDocName$");
		}
		
			$docName$Info.set_toSave(false);
			$docName$Info.setCurrent$upperDocName$BodyInfo($docName$BodyInfo);
			$docName$Info.set$upperDocName$BodyModificationInfo($docName$BodyModificationInfo);
			$docName$BodyInfo.setSubmit("");
			$docName$BodyInfo.set$upperDocName$BodyModificationInfo(new $upperDocName$BodyInfo());
			$upperDocName$BodyInfo submitModification = new $upperDocName$BodyInfo();
			submitModification.setSubmit("");
			$docName$BodyModificationInfo.set$upperDocName$BodyModificationInfo(submitModification);
		
			modelAndView.addObject("$docName$Info",$docName$Info);
			modelAndView.addObject("$docName$BodyList", $docName$Info.get$upperDocName$BodyList());
			modelAndView.addObject("$docName$BodyInfo",$docName$BodyInfo);
			modelAndView.addObject("$docName$BodyModificationInfo",$docName$BodyModificationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private ModelAndView setErrors(ModelAndView model, List<ObjectError> errors, $upperDocName$Info $docName$Info, String origin, String modifiedId){
		for (ObjectError error : errors) {
			if (error instanceof FieldError){
				FieldError fieldError=(FieldError) error;
				if (fieldError.getCode()!=null){
					if (origin.equals(CyberUtils.additionForm)){
						model.addObject("_AdditionFormException", true);
					}
					
					if (origin.equals(CyberUtils.modificationForm)){
						model.addObject("_ModificationFormException", true);
						model.addObject("_ModifiedId",modifiedId);
					}
					
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
	
	@ModelAttribute("$docName$Info")
	private $upperDocName$Info get$upperDocName$Info(String id, @ModelAttribute("$docName$Info") $upperDocName$Info $docName$Info, Model model, HttpServletRequest request)  throws Exception {
		$upperDocName$Info $docName$InfoSession = ($upperDocName$Info) request.getSession().getAttribute("$docName$Info");

			if (id!=null || ($docName$Info.getCreated()!=null && $docName$Info.getCreated() && $docName$InfoSession!=null && $docName$InfoSession.getId()!=null)){
				$docName$Info = $docName$Service.request$upperDocName$Details(id==null?new Request$upperDocName$Event($docName$InfoSession.getId()):new Request$upperDocName$Event(id)).get$upperDocName$();
				$docName$Info.setShowBody(true);
				$docName$Info.setCreated(true);
			}
			else{
				$docName$Info = new $upperDocName$Info();
				
				$setDefaults$
				$setOnLoadHeaderValues$
			}
			
			$setListsAndDefaults$
			
			$upperDocName$BodyInfo $docName$BodyInfo = get$upperDocName$BodyInfo(request);	
			$docName$Info.setCurrent$upperDocName$BodyInfo($docName$BodyInfo);
			$upperDocName$BodyInfo $docName$BodyModificationInfo = get$upperDocName$BodyModificationInfo(request);
			$docName$Info.set$upperDocName$BodyModificationInfo($docName$BodyModificationInfo);
			$docName$BodyInfo.setSubmit("");
			$upperDocName$BodyInfo submitModification = new $upperDocName$BodyInfo();
			submitModification.setSubmit("");
			$docName$BodyModificationInfo.set$upperDocName$BodyModificationInfo(submitModification);
		
			request.getSession().setAttribute("$docName$Info", $docName$Info);
			model.addAttribute("$docName$Info",$docName$Info);
			model.addAttribute("$docName$BodyList", $docName$Info.get$upperDocName$BodyList());
			model.addAttribute("$docName$BodyInfo", $docName$BodyInfo);
			model.addAttribute("$docName$BodyModificationInfo",$docName$BodyModificationInfo);
			
			return $docName$Info;
	}
		
		private $upperDocName$BodyInfo get$upperDocName$BodyInfo(HttpServletRequest request) throws Exception {
			$upperDocName$BodyInfo $docName$BodyInfo = new $upperDocName$BodyInfo();
			
			$bodyAdditionLists$
			
			$docName$BodyInfo.set_enableDeletion(Boolean.TRUE);
			$docName$BodyInfo.setId(null);
			$setBodyDefaults$
			
			$setOnLoadBodyValues$

			request.getSession().setAttribute("$docName$BodyInfo", $docName$BodyInfo);
			
			return $docName$BodyInfo;
		}
		

		private $upperDocName$BodyInfo get$upperDocName$BodyModificationInfo(HttpServletRequest request) throws Exception{
			$upperDocName$BodyInfo $docName$BodyModificationInfo = new $upperDocName$BodyInfo();
			
			$bodyModificationLists$

			request.getSession().setAttribute("$docName$BodyModificationInfo", $docName$BodyModificationInfo);
			
			return $docName$BodyModificationInfo;
		}
}
