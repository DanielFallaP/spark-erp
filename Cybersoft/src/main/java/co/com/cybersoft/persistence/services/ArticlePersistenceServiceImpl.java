package co.com.cybersoft.persistence.services;

import co.com.cybersoft.events.ArticleCreatedEvent;
import co.com.cybersoft.events.CreateArticleEvent;
import co.com.cybersoft.persistence.domain.Article;
import co.com.cybersoft.persistence.repository.ArticleRepository;

public class ArticlePersistenceServiceImpl implements ArticlePersistenceService{

	private final ArticleRepository articleRepository;
	
	public ArticlePersistenceServiceImpl(final ArticleRepository articleRepository){
		this.articleRepository=articleRepository;
	}
	
	@Override
	public ArticleCreatedEvent createOrder(CreateArticleEvent event) {
		Article article = Article.fromArticleDetails(event.getDetails());
		article = articleRepository.save(article);
		return new ArticleCreatedEvent(article.getId(), article.toArticleDetails());
	}

}
