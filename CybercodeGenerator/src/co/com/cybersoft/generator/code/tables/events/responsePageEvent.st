package co.com.cybersoft.$module$.tables.events.$tableName$;

import org.springframework.data.domain.Page;

import co.com.cybersoft.$module$.tables.persistence.domain.$entityName$;
$referencesImports$

/**
 * Event class for $entityName$
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class $entityName$PageEvent {
	private Page<$entityName$> $tableName$Page;
	
	private List<$entityName$> allList;
	
	private Long totalCount;
	
	$referencesDeclarations$
	
	public $entityName$PageEvent(){
    }
	$referencesConstructors$
	
	$referencesGettersSetters$
	
	public List<$entityName$> getAllList() {
		return allList;
	}

	public void setAllList(List<$entityName$> allList) {
		this.allList = allList;
	}
	
	public $entityName$PageEvent(Page<$entityName$>  $tableName$Page){
		this.$tableName$Page= $tableName$Page;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public $entityName$PageEvent(Page<$entityName$>  $tableName$Page, Long totalCount){
		this.$tableName$Page= $tableName$Page;
		this.totalCount=totalCount;
	}

	public Page<$entityName$> get$entityName$Page() {
		return $tableName$Page;
	}
	
	
}