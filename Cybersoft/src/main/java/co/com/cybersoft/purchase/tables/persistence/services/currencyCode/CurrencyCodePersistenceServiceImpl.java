package co.com.cybersoft.purchase.tables.persistence.services.currencyCode;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.CurrencyCodeDetails;
import co.com.cybersoft.purchase.tables.events.currencyCode.CreateCurrencyCodeEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.CurrencyCodeModificationEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodeDetailsEvent;
import co.com.cybersoft.purchase.tables.events.currencyCode.RequestCurrencyCodePageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.currencyCode.CurrencyCodeCustomRepo;
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
public class CurrencyCodePersistenceServiceImpl implements CurrencyCodePersistenceService{

	private final CurrencyCodeRepository currencyCodeRepository;
	
	private final CurrencyCodeCustomRepo currencyCodeCustomRepo;
	
	
	public CurrencyCodePersistenceServiceImpl(final CurrencyCodeRepository currencyCodeRepository, final CurrencyCodeCustomRepo currencyCodeCustomRepo) {
		this.currencyCodeRepository=currencyCodeRepository;
		this.currencyCodeCustomRepo=currencyCodeCustomRepo;
	}
	
	public CurrencyCodeDetailsEvent createCurrencyCode(CreateCurrencyCodeEvent event) throws Exception{
		CurrencyCode currencyCode = new CurrencyCode().fromCurrencyCodeDetails(event.getCurrencyCodeDetails());
		currencyCode = currencyCodeRepository.save(currencyCode);
		currencyCode = currencyCodeRepository.findOne(currencyCode.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",currencyCode.getId());
		return new CurrencyCodeDetailsEvent(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"currencyCode", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CurrencyCodePageEvent requestCurrencyCodePage(RequestCurrencyCodePageEvent event) throws Exception {
		Page<CurrencyCode> currencyCodes = currencyCodeRepository.findAll(event.getPageable());
		return new CurrencyCodePageEvent(currencyCodes);
	}

	public CurrencyCodeDetailsEvent requestCurrencyCodeDetails(RequestCurrencyCodeDetailsEvent event) throws Exception {
		CurrencyCodeDetails currencyCodeDetails=null;
		if (event.getId()!=null){
			CurrencyCode currencyCode = currencyCodeRepository.findOne(event.getId());
			if (currencyCode!=null)
				currencyCodeDetails = new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode);
		}
		else{
							CurrencyCode currencyCode = new CurrencyCodePersistenceFactory(currencyCodeRepository).getCurrencyCodeByField(event.getField(),event.getValue());
							if (currencyCode!=null)
								currencyCodeDetails = new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode);
						}
		return new CurrencyCodeDetailsEvent(currencyCodeDetails);
		
	}

	public CurrencyCodeDetailsEvent modifyCurrencyCode(CurrencyCodeModificationEvent event) throws Exception {
		CurrencyCode currencyCode = new CurrencyCode().fromCurrencyCodeDetails(event.getCurrencyCodeDetails());
		currencyCode = currencyCodeRepository.save(currencyCode);
		currencyCode = currencyCodeRepository.findOne(currencyCode.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",currencyCode.getId());
		return new CurrencyCodeDetailsEvent(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode));
	}
	
		public CurrencyCodeDetailsEvent getOnlyRecord() throws Exception {
			Iterable<CurrencyCode> all = currencyCodeRepository.findAll();
			CurrencyCodeDetails single = new CurrencyCodeDetails();
			for (CurrencyCode currencyCode : all) {
				single=new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode);
				break;
			}
			return new CurrencyCodeDetailsEvent(single);
		}
	
	public CurrencyCodePageEvent requestAllByCurrencyName(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByCurrencyName(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByCountry(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllByCurrency(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByCurrency(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllBySymbol(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveBySymbol(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllByHex1(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByHex1(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllByHex2(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByHex2(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}public CurrencyCodePageEvent requestAllByHex3(EmbeddedField... fields) throws Exception {
			List<CurrencyCode> all = currencyCodeCustomRepo.findAllActiveByHex3(fields);
			List<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			for (CurrencyCode currencyCode : all) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCode, fields));
			}
			return new CurrencyCodePageEvent(list);
		}
	
	public CurrencyCodePageEvent requestByContainingCurrencyName(String currencyName) throws Exception {
			ArrayList<CurrencyCodeDetails> list = new ArrayList<CurrencyCodeDetails>();
			List<CurrencyCode> currencyNameList = currencyCodeCustomRepo.findByContainingCurrencyName(currencyName);
			for (CurrencyCode currencyCodeEntity : currencyNameList) {
				list.add(new CurrencyCodeDetails().toCurrencyCodeDetails(currencyCodeEntity));
			}
			return new CurrencyCodePageEvent(list);
		}

	public CurrencyCodePageEvent requestCurrencyCodeFilterPage(RequestCurrencyCodePageEvent event) throws Exception {
		Page<CurrencyCode> page = currencyCodeCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CurrencyCodePageEvent(page, currencyCodeCustomRepo.getTotalCount());
	}
	
	public CurrencyCodePageEvent requestCurrencyCodeFilter(
			RequestCurrencyCodePageEvent event) throws Exception {
		List<CurrencyCode> all = currencyCodeCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CurrencyCodePageEvent pageEvent = new CurrencyCodePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}