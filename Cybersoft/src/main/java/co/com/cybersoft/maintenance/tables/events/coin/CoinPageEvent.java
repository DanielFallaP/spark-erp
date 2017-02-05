package co.com.cybersoft.maintenance.tables.events.coin;

import org.springframework.data.domain.Page;

import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;
import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import java.util.List;

/**
 * Event class for Coin
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public class CoinPageEvent {
	private Page<Coin> coinPage;
	
	private List<Coin> allList;
	
	private Long totalCount;
	
	private List<CoinDetails> coinList;



	
	public CoinPageEvent(){
    }
	public CoinPageEvent(List<CoinDetails>  coinList){
			this.coinList= coinList;
	}



	
	public List<CoinDetails> getCoinList() {
	return coinList;
	}



	
	public List<Coin> getAllList() {
		return allList;
	}

	public void setAllList(List<Coin> allList) {
		this.allList = allList;
	}
	
	public CoinPageEvent(Page<Coin>  coinPage){
		this.coinPage= coinPage;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
		
	public CoinPageEvent(Page<Coin>  coinPage, Long totalCount){
		this.coinPage= coinPage;
		this.totalCount=totalCount;
	}

	public Page<Coin> getCoinPage() {
		return coinPage;
	}
	
	
}