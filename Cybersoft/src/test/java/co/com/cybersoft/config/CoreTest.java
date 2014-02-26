package co.com.cybersoft.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.cybersoft.core.services.ArticleService;
import co.com.cybersoft.events.ArticleCreatedEvent;
import co.com.cybersoft.events.ArticleDetails;
import co.com.cybersoft.events.CreateArticleEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PersistenceConfig.class,CoreConfig.class})
public class CoreTest {

	@Autowired
	ArticleService articleService;
	
	@Test
	public void thatIsWellConfigured(){
		ArticleCreatedEvent createArticle = articleService.createArticle(new CreateArticleEvent(new ArticleDetails()));
		assertEquals(true,true);
	}
}
