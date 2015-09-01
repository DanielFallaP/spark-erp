package co.com.cybersoft.purchase.tables.persistence.services.exchangeRate;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.ExchangeRateDetails;
import co.com.cybersoft.purchase.tables.events.exchangeRate.CreateExchangeRateEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.ExchangeRateModificationEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRateDetailsEvent;
import co.com.cybersoft.purchase.tables.events.exchangeRate.RequestExchangeRatePageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateCustomRepo;
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
public class ExchangeRatePersistenceServiceImpl implements ExchangeRatePersistenceService{

	private final ExchangeRateRepository exchangeRateRepository;
	
	private final ExchangeRateCustomRepo exchangeRateCustomRepo;
	
	
	public ExchangeRatePersistenceServiceImpl(final ExchangeRateRepository exchangeRateRepository, final ExchangeRateCustomRepo exchangeRateCustomRepo) {
		this.exchangeRateRepository=exchangeRateRepository;
		this.exchangeRateCustomRepo=exchangeRateCustomRepo;
	}
	
	@Override
	public ExchangeRateDetailsEvent createExchangeRate(CreateExchangeRateEvent event) throws Exception{
		ExchangeRate exchangeRate = new ExchangeRate().fromExchangeRateDetails(event.getExchangeRateDetails());
		exchangeRate = exchangeRateRepository.save(exchangeRate);
		exchangeRate = exchangeRateRepository.findOne(exchangeRate.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",exchangeRate.getId());
		return new ExchangeRateDetailsEvent(new ExchangeRateDetails().toExchangeRateDetails(exchangeRate));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"exchangeRate", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	@Override
	public ExchangeRatePageEvent requestExchangeRatePage(RequestExchangeRatePageEvent event) throws Exception {
		Page<ExchangeRate> exchangeRates = exchangeRateRepository.findAll(event.getPageable());
		return new ExchangeRatePageEvent(exchangeRates);
	}

	@Override
	public ExchangeRateDetailsEvent requestExchangeRateDetails(RequestExchangeRateDetailsEvent event) throws Exception {
		ExchangeRateDetails exchangeRateDetails=null;
		if (event.getId()!=null){
			ExchangeRate exchangeRate = exchangeRateRepository.findOne(event.getId());
			if (exchangeRate!=null)
				exchangeRateDetails = new ExchangeRateDetails().toExchangeRateDetails(exchangeRate);
		}
		return new ExchangeRateDetailsEvent(exchangeRateDetails);
		
	}

	@Override
	public ExchangeRateDetailsEvent modifyExchangeRate(ExchangeRateModificationEvent event) throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate().fromExchangeRateDetails(event.getExchangeRateDetails());
		exchangeRate = exchangeRateRepository.save(exchangeRate);
		exchangeRate = exchangeRateRepository.findOne(exchangeRate.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",exchangeRate.getId());
		return new ExchangeRateDetailsEvent(new ExchangeRateDetails().toExchangeRateDetails(exchangeRate));
	}
	
	@Override
		public ExchangeRateDetailsEvent getOnlyRecord() throws Exception {
			Iterable<ExchangeRate> all = exchangeRateRepository.findAll();
			ExchangeRateDetails single = new ExchangeRateDetails();
			for (ExchangeRate exchangeRate : all) {
				single=new ExchangeRateDetails().toExchangeRateDetails(exchangeRate);
				break;
			}
			return new ExchangeRateDetailsEvent(single);
		}
	
	@Override
		public ExchangeRatePageEvent requestAllByLocalCurrency(EmbeddedField... fields) throws Exception {
			List<ExchangeRate> all = exchangeRateCustomRepo.findAllActiveByLocalCurrency(fields);
			List<ExchangeRateDetails> list = new ArrayList<ExchangeRateDetails>();
			for (ExchangeRate exchangeRate : all) {
				list.add(new ExchangeRateDetails().toExchangeRateDetails(exchangeRate));
			}
			return new ExchangeRatePageEvent(list);
		}@Override
		public ExchangeRatePageEvent requestAllByCodeName(String code) throws Exception {
			List<ExchangeRate> all = exchangeRateCustomRepo.findByCodeName(code);
			List<ExchangeRateDetails> list = new ArrayList<ExchangeRateDetails>();
			for (ExchangeRate exchangeRate : all) {
				list.add(new ExchangeRateDetails().toExchangeRateDetails(exchangeRate));
			}
			return new ExchangeRatePageEvent(list);
		}@Override
		public ExchangeRatePageEvent requestAllByForeignCurrency(EmbeddedField... fields) throws Exception {
			List<ExchangeRate> all = exchangeRateCustomRepo.findAllActiveByForeignCurrency(fields);
			List<ExchangeRateDetails> list = new ArrayList<ExchangeRateDetails>();
			for (ExchangeRate exchangeRate : all) {
				list.add(new ExchangeRateDetails().toExchangeRateDetails(exchangeRate));
			}
			return new ExchangeRatePageEvent(list);
		}
	

	@Override
	public ExchangeRatePageEvent requestExchangeRateFilterPage(RequestExchangeRatePageEvent event) throws Exception {
		Page<ExchangeRate> page = exchangeRateCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new ExchangeRatePageEvent(page);
	}

	@Override
	public ExchangeRatePageEvent requestExchangeRateFilter(
			RequestExchangeRatePageEvent event) throws Exception {
		List<ExchangeRate> all = exchangeRateCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		ExchangeRatePageEvent pageEvent = new ExchangeRatePageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}	
}