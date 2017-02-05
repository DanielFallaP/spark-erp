package co.com.cybersoft.maintenance.tables.persistence.services.coin;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CoinDetails;
import co.com.cybersoft.maintenance.tables.events.coin.CreateCoinEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinPageEvent;
import co.com.cybersoft.maintenance.tables.events.coin.CoinModificationEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.coin.RequestCoinPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.coin.CoinCustomRepo;
//import co.com.cybersoft.man.services.security.SessionAction;
//import co.com.cybersoft.man.services.security.SessionLogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.cybersoft.util.CyberUtils;
import co.com.cybersoft.util.EmbeddedField;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public class CoinPersistenceServiceImpl implements CoinPersistenceService{

	private final CoinRepository coinRepository;
	
	private final CoinCustomRepo coinCustomRepo;
	
	
	public CoinPersistenceServiceImpl(final CoinRepository coinRepository, final CoinCustomRepo coinCustomRepo) {
		this.coinRepository=coinRepository;
		this.coinCustomRepo=coinCustomRepo;
	}
	
	public CoinDetailsEvent createCoin(CreateCoinEvent event) throws Exception{
		Coin coin = new Coin().fromCoinDetails(event.getCoinDetails());
		coin = coinRepository.save(coin);
		coin = coinRepository.findOne(coin.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",coin.getId());
		return new CoinDetailsEvent(new CoinDetails().toCoinDetails(coin));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"coin", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CoinPageEvent requestCoinPage(RequestCoinPageEvent event) throws Exception {
		Page<Coin> coins = coinRepository.findAll(event.getPageable());
		return new CoinPageEvent(coins);
	}

	public CoinDetailsEvent requestCoinDetails(RequestCoinDetailsEvent event) throws Exception {
		CoinDetails coinDetails=null;
		if (event.getId()!=null){
			Coin coin = coinRepository.findOne(event.getId());
			if (coin!=null)
				coinDetails = new CoinDetails().toCoinDetails(coin);
		}
		return new CoinDetailsEvent(coinDetails);
		
	}

	public CoinDetailsEvent modifyCoin(CoinModificationEvent event) throws Exception {
		Coin coin = new Coin().fromCoinDetails(event.getCoinDetails());
		coin = coinRepository.save(coin);
		coin = coinRepository.findOne(coin.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",coin.getId());
		return new CoinDetailsEvent(new CoinDetails().toCoinDetails(coin));
	}
	
		public CoinDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Coin> all = coinRepository.findAll();
			CoinDetails single = new CoinDetails();
			for (Coin coin : all) {
				single=new CoinDetails().toCoinDetails(coin);
				break;
			}
			return new CoinDetailsEvent(single);
		}
	
	public CoinPageEvent requestAllByCompany(EmbeddedField... fields) throws Exception {
			List<Coin> all = coinCustomRepo.findAllActiveByCompany(fields);
			List<CoinDetails> list = new ArrayList<CoinDetails>();
			for (Coin coin : all) {
				list.add(new CoinDetails().toCoinDetails(coin, fields));
			}
			return new CoinPageEvent(list);
		}public CoinPageEvent requestAllByNameCoin(EmbeddedField... fields) throws Exception {
			List<Coin> all = coinCustomRepo.findAllActiveByNameCoin(fields);
			List<CoinDetails> list = new ArrayList<CoinDetails>();
			for (Coin coin : all) {
				list.add(new CoinDetails().toCoinDetails(coin, fields));
			}
			return new CoinPageEvent(list);
		}public CoinPageEvent requestAllByAbbreviationCurrency(EmbeddedField... fields) throws Exception {
			List<Coin> all = coinCustomRepo.findAllActiveByAbbreviationCurrency(fields);
			List<CoinDetails> list = new ArrayList<CoinDetails>();
			for (Coin coin : all) {
				list.add(new CoinDetails().toCoinDetails(coin, fields));
			}
			return new CoinPageEvent(list);
		}
	

	public CoinPageEvent requestCoinFilterPage(RequestCoinPageEvent event) throws Exception {
		Page<Coin> page = coinCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CoinPageEvent(page, coinCustomRepo.getTotalCount());
	}
	
	public CoinPageEvent requestCoinFilter(
			RequestCoinPageEvent event) throws Exception {
		List<Coin> all = coinCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CoinPageEvent pageEvent = new CoinPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}