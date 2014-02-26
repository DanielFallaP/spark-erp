package co.com.cybersoft.events;

public class CreateArticleEvent {

	private ArticleDetails details;
	
	public CreateArticleEvent(ArticleDetails details){
		this.details=details;
	}
	
	public ArticleDetails getDetails(){
		return details;
	}
}
