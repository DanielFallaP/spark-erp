package co.com.cybersoft.core.services;

import co.com.cybersoft.events.ArticleCreatedEvent;
import co.com.cybersoft.events.CreateArticleEvent;
import co.com.cybersoft.persistence.services.ArticlePersistenceService;

public class ArticleServiceImpl implements ArticleService{

	private final ArticlePersistenceService articlePersistenceService;
	
	public ArticleServiceImpl(final ArticlePersistenceService articlePersistenceService){
		this.articlePersistenceService=articlePersistenceService;
	}
	
	/**
	 */
	@Override
	public ArticleCreatedEvent createArticle(CreateArticleEvent createArticleEvent) {
		return articlePersistenceService.createOrder(createArticleEvent);
	}

}
