package co.com.cybersoft.purchase.tables.persistence.services.country;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.purchase.tables.core.domain.CountryDetails;
import co.com.cybersoft.purchase.tables.events.country.CreateCountryEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryPageEvent;
import co.com.cybersoft.purchase.tables.events.country.CountryModificationEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryDetailsEvent;
import co.com.cybersoft.purchase.tables.events.country.RequestCountryPageEvent;
import co.com.cybersoft.purchase.tables.persistence.domain.Country;
import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.country.CountryCustomRepo;
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
public class CountryPersistenceServiceImpl implements CountryPersistenceService{

	private final CountryRepository countryRepository;
	
	private final CountryCustomRepo countryCustomRepo;
	
	
	public CountryPersistenceServiceImpl(final CountryRepository countryRepository, final CountryCustomRepo countryCustomRepo) {
		this.countryRepository=countryRepository;
		this.countryCustomRepo=countryCustomRepo;
	}
	
	public CountryDetailsEvent createCountry(CreateCountryEvent event) throws Exception{
		Country country = new Country().fromCountryDetails(event.getCountryDetails());
		country = countryRepository.save(country);
		country = countryRepository.findOne(country.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",country.getId());
		return new CountryDetailsEvent(new CountryDetails().toCountryDetails(country));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"country", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CountryPageEvent requestCountryPage(RequestCountryPageEvent event) throws Exception {
		Page<Country> countrys = countryRepository.findAll(event.getPageable());
		return new CountryPageEvent(countrys);
	}

	public CountryDetailsEvent requestCountryDetails(RequestCountryDetailsEvent event) throws Exception {
		CountryDetails countryDetails=null;
		if (event.getId()!=null){
			Country country = countryRepository.findOne(event.getId());
			if (country!=null)
				countryDetails = new CountryDetails().toCountryDetails(country);
		}
		else{
							Country country = new CountryPersistenceFactory(countryRepository).getCountryByField(event.getField(),event.getValue());
							if (country!=null)
								countryDetails = new CountryDetails().toCountryDetails(country);
						}
		return new CountryDetailsEvent(countryDetails);
		
	}

	public CountryDetailsEvent modifyCountry(CountryModificationEvent event) throws Exception {
		Country country = new Country().fromCountryDetails(event.getCountryDetails());
		country = countryRepository.save(country);
		country = countryRepository.findOne(country.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",country.getId());
		return new CountryDetailsEvent(new CountryDetails().toCountryDetails(country));
	}
	
		public CountryDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Country> all = countryRepository.findAll();
			CountryDetails single = new CountryDetails();
			for (Country country : all) {
				single=new CountryDetails().toCountryDetails(country);
				break;
			}
			return new CountryDetailsEvent(single);
		}
	
	public CountryPageEvent requestAllByRegion(EmbeddedField... fields) throws Exception {
			List<Country> all = countryCustomRepo.findAllActiveByRegion(fields);
			List<CountryDetails> list = new ArrayList<CountryDetails>();
			for (Country country : all) {
				list.add(new CountryDetails().toCountryDetails(country, fields));
			}
			return new CountryPageEvent(list);
		}public CountryPageEvent requestAllByCountry(EmbeddedField... fields) throws Exception {
			List<Country> all = countryCustomRepo.findAllActiveByCountry(fields);
			List<CountryDetails> list = new ArrayList<CountryDetails>();
			for (Country country : all) {
				list.add(new CountryDetails().toCountryDetails(country, fields));
			}
			return new CountryPageEvent(list);
		}public CountryPageEvent requestAllByLocalName(EmbeddedField... fields) throws Exception {
			List<Country> all = countryCustomRepo.findAllActiveByLocalName(fields);
			List<CountryDetails> list = new ArrayList<CountryDetails>();
			for (Country country : all) {
				list.add(new CountryDetails().toCountryDetails(country, fields));
			}
			return new CountryPageEvent(list);
		}public CountryPageEvent requestAllByFrenchName(EmbeddedField... fields) throws Exception {
			List<Country> all = countryCustomRepo.findAllActiveByFrenchName(fields);
			List<CountryDetails> list = new ArrayList<CountryDetails>();
			for (Country country : all) {
				list.add(new CountryDetails().toCountryDetails(country, fields));
			}
			return new CountryPageEvent(list);
		}
	
	public CountryPageEvent requestByContainingCountry(String country) throws Exception {
			ArrayList<CountryDetails> list = new ArrayList<CountryDetails>();
			List<Country> countryList = countryCustomRepo.findByContainingCountry(country);
			for (Country countryEntity : countryList) {
				list.add(new CountryDetails().toCountryDetails(countryEntity));
			}
			return new CountryPageEvent(list);
		}public CountryPageEvent requestByContainingLocalName(String localName) throws Exception {
			ArrayList<CountryDetails> list = new ArrayList<CountryDetails>();
			List<Country> localNameList = countryCustomRepo.findByContainingLocalName(localName);
			for (Country countryEntity : localNameList) {
				list.add(new CountryDetails().toCountryDetails(countryEntity));
			}
			return new CountryPageEvent(list);
		}public CountryPageEvent requestByContainingFrenchName(String frenchName) throws Exception {
			ArrayList<CountryDetails> list = new ArrayList<CountryDetails>();
			List<Country> frenchNameList = countryCustomRepo.findByContainingFrenchName(frenchName);
			for (Country countryEntity : frenchNameList) {
				list.add(new CountryDetails().toCountryDetails(countryEntity));
			}
			return new CountryPageEvent(list);
		}

	public CountryPageEvent requestCountryFilterPage(RequestCountryPageEvent event) throws Exception {
		Page<Country> page = countryCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CountryPageEvent(page, countryCustomRepo.getTotalCount());
	}
	
	public CountryPageEvent requestCountryFilter(
			RequestCountryPageEvent event) throws Exception {
		List<Country> all = countryCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CountryPageEvent pageEvent = new CountryPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}