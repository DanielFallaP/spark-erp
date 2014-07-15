package co.com.cybersoft.docs.events.$docName$;

import org.springframework.data.domain.Page;

import co.com.cybersoft.docs.persistence.domain.$upperDocName$;
import co.com.cybersoft.docs.web.domain.$docName$.$upperDocName$Info;

import java.util.List;

/**
 * Event class for $upperDocName$
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class $upperDocName$PageEvent {
	private Page<$upperDocName$> $docName$Page;
	
	private List<$upperDocName$Info> $docName$List;



	
	public $upperDocName$PageEvent(List<$upperDocName$Info>  $docName$List){
			this.$docName$List= $docName$List;
	}



	
	public List<$upperDocName$Info> get$upperDocName$List() {
	return $docName$List;
	}



	
	public $upperDocName$PageEvent(Page<$upperDocName$>  $docName$Page){
		this.$docName$Page= $docName$Page;
	}

	public Page<$upperDocName$> get$upperDocName$Page() {
		return $docName$Page;
	}
	
}