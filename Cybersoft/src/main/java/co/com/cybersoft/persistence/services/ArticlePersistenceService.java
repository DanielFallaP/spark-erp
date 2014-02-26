package co.com.cybersoft.persistence.services;

import co.com.cybersoft.events.ArticleCreatedEvent;
import co.com.cybersoft.events.CreateArticleEvent;

public interface ArticlePersistenceService {

	public ArticleCreatedEvent createOrder(CreateArticleEvent event);
}
