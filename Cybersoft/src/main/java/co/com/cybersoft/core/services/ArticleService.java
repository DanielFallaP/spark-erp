package co.com.cybersoft.core.services;

import co.com.cybersoft.events.ArticleCreatedEvent;
import co.com.cybersoft.events.CreateArticleEvent;

public interface ArticleService {
	ArticleCreatedEvent createArticle(CreateArticleEvent createArticleEvent);
}
