package co.com.cybersoft.events;

public class ArticleCreatedEvent  {
	
	private final String code;
	private final ArticleDetails articleDetails;
	
	public ArticleCreatedEvent(final String code, final ArticleDetails articleDetails){
		this.code=code;
		this.articleDetails=articleDetails;
	}

	public ArticleDetails getArticleDetails() {
		return articleDetails;
	}

	public String getCode() {
		return code;
	}
	
	
	
}
