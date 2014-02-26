package co.com.cybersoft.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.cybersoft.core.services.ArticleService;
import co.com.cybersoft.events.ArticleDetails;
import co.com.cybersoft.events.CreateArticleEvent;
import co.com.cybersoft.web.domain.ArticleInfo;

@Controller
@RequestMapping("/articleCreation")
public class ArticleCreationController {

	private static final Logger LOG = LoggerFactory.getLogger(ArticleCreationController.class);
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String articleCreation(){
		return "/articleCreation";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createArticle(@ModelAttribute("articleInfo") ArticleInfo articleInfo){
		LOG.debug("Creation of article");
		
		ArticleDetails articleDetails = createArticleDetails(articleInfo);
		articleService.createArticle(new CreateArticleEvent(articleDetails));
		
		return "/articleCreation";
	}
	
	private ArticleDetails createArticleDetails(ArticleInfo articleInfo){
		ArticleDetails articleDetails = new ArticleDetails();
		LOG.debug(articleInfo.getCode());
		BeanUtils.copyProperties(articleInfo, articleDetails);
		//articleDetails.setDateOfCreation(new Date());
		return articleDetails;
	}
	
	@ModelAttribute("articleInfo")
	private ArticleInfo getArticleInfo(){
		return new ArticleInfo();
	}
}
