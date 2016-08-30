package co.com.cybersoft.purchase.tables.events.currencyCode;

import org.springframework.data.domain.Page;

import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import java.util.List;

/**
 * Event class for CurrencyCode
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CurrencyCodePageEvent {
	private Page<CurrencyCode> currencyCodePage;
	
	private List<CurrencyCode> allList;
	
	private Long totalCount;
	
	private List<CurrencyCodeDetails> currencyCodeList;



	
	public CurrencyCodePageEvent(){
    }
	public CurrencyCodePageEvent(List<CurrencyCodeDetails>  currencyCodeList){
			this.currencyCodeList= currencyCodeList;
	}



	
	public List<CurrencyCodeDetails> getCurrencyCodeList() {
	return currencyCodeList;
	}



	
	public List<CurrencyCode> getAllList() {
		return allList;
	}

	public void setAllList(List<CurrencyCode> allList) {
		this.allList = allList;
	}
	
	public CurrencyCodePageEvent(Page<CurrencyCode>  currencyCodePage){
		this.currencyCodePage= currencyCodePage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CurrencyCodePageEvent(Page<CurrencyCode>  currencyCodePage, Long totalCount){
		this.currencyCodePage= currencyCodePage;
		this.totalCount=totalCount;
	}

	public Page<CurrencyCode> getCurrencyCodePage() {
		return currencyCodePage;
	}
	
	
}