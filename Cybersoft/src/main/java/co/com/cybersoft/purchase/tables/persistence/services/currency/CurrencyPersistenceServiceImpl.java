package co.com.cybersoft.purchase.tables.persistence.services.currency;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyDetails;
import co.com.cybersoft.purchase.tables.events.currency.CreateCurrencyEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyPageEvent;
import co.com.cybersoft.purchase.tables.events.currency.CurrencyModificationEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currency.RequestCurrencyPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.currency.CurrencyCustomRepo;
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
public class CurrencyPersistenceServiceImpl implements CurrencyPersistenceService{

	private final CurrencyRepository currencyRepository;
	
	private final CurrencyCustomRepo currencyCustomRepo;
	
	
	public CurrencyPersistenceServiceImpl(final CurrencyRepository currencyRepository, final CurrencyCustomRepo currencyCustomRepo) {
		this.currencyRepository=currencyRepository;
		this.currencyCustomRepo=currencyCustomRepo;
	}
	
	public CurrencyDetailsEvent createCurrency(CreateCurrencyEvent event) throws Exception{
		Currency currency = new Currency().fromCurrencyDetails(event.getCurrencyDetails());
		currency = currencyRepository.save(currency);
		currency = currencyRepository.findOne(currency.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",currency.getId());
		return new CurrencyDetailsEvent(new CurrencyDetails().toCurrencyDetails(currency));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"currency", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CurrencyPageEvent requestCurrencyPage(RequestCurrencyPageEvent event) throws Exception {
		Page<Currency> currencys = currencyRepository.findAll(event.getPageable());
		return new CurrencyPageEvent(currencys);
	}

	public CurrencyDetailsEvent requestCurrencyDetails(RequestCurrencyDetailsEvent event) throws Exception {
		CurrencyDetails currencyDetails=null;
		if (event.getId()!=null){
			Currency currency = currencyRepository.findOne(event.getId());
			if (currency!=null)
				currencyDetails = new CurrencyDetails().toCurrencyDetails(currency);
		}
		else{
							Currency currency = new CurrencyPersistenceFactory(currencyRepository).getCurrencyByField(event.getField(),event.getValue());
							if (currency!=null)
								currencyDetails = new CurrencyDetails().toCurrencyDetails(currency);
						}
		return new CurrencyDetailsEvent(currencyDetails);
		
	}

	public CurrencyDetailsEvent modifyCurrency(CurrencyModificationEvent event) throws Exception {
		Currency currency = new Currency().fromCurrencyDetails(event.getCurrencyDetails());
		currency = currencyRepository.save(currency);
		currency = currencyRepository.findOne(currency.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",currency.getId());
		return new CurrencyDetailsEvent(new CurrencyDetails().toCurrencyDetails(currency));
	}
	
		public CurrencyDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Currency> all = currencyRepository.findAll();
			CurrencyDetails single = new CurrencyDetails();
			for (Currency currency : all) {
				single=new CurrencyDetails().toCurrencyDetails(currency);
				break;
			}
			return new CurrencyDetailsEvent(single);
		}
	
	public CurrencyPageEvent requestAllByCode(EmbeddedField... fields) throws Exception {
			List<Currency> all = currencyCustomRepo.findAllActiveByCode(fields);
			List<CurrencyDetails> list = new ArrayList<CurrencyDetails>();
			for (Currency currency : all) {
				list.add(new CurrencyDetails().toCurrencyDetails(currency, fields));
			}
			return new CurrencyPageEvent(list);
		}public CurrencyPageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception {
			List<Currency> all = currencyCustomRepo.findAllActiveByCurrency(fields);
			List<CurrencyDetails> list = new ArrayList<CurrencyDetails>();
			for (Currency currency : all) {
				list.add(new CurrencyDetails().toCurrencyDetails(currency, fields));
			}
			return new CurrencyPageEvent(list);
		}
	
	public CurrencyPageEvent requestByContainingCurrency(String currency) throws Exception {
			ArrayList<CurrencyDetails> list = new ArrayList<CurrencyDetails>();
			List<Currency> currencyList = currencyCustomRepo.findByContainingCurrency(currency);
			for (Currency currencyEntity : currencyList) {
				list.add(new CurrencyDetails().toCurrencyDetails(currencyEntity));
			}
			return new CurrencyPageEvent(list);
		}

	public CurrencyPageEvent requestCurrencyFilterPage(RequestCurrencyPageEvent event) throws Exception {
		Page<Currency> page = currencyCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CurrencyPageEvent(page, currencyCustomRepo.getTotalCount());
	}
	
	public CurrencyPageEvent requestCurrencyFilter(
			RequestCurrencyPageEvent event) throws Exception {
		List<Currency> all = currencyCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CurrencyPageEvent pageEvent = new CurrencyPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}