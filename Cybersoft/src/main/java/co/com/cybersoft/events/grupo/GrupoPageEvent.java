package co.com.cybersoft.events.grupo;

import org.springframework.data.domain.Page;

import co.com.cybersoft.persistence.domain.Grupo;
import co.com.cybersoft.core.domain.GrupoDetails;
import co.com.cybersoft.persistence.domain.Grupo;
import java.util.List;

/**
 * Event class for Grupo
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class GrupoPageEvent {
	private Page<Grupo> grupoPage;
	
	private List<GrupoDetails> grupoList;



	
	public GrupoPageEvent(List<GrupoDetails>  grupoList){
			this.grupoList= grupoList;
	}



	
	public List<GrupoDetails> getGrupoList() {
	return grupoList;
	}



	
	public GrupoPageEvent(Page<Grupo>  grupoPage){
		this.grupoPage= grupoPage;
	}

	public Page<Grupo> getGrupoPage() {
		return grupoPage;
	}
	
}