package co.com.cybersoft.maintenance.tables.persistence.services.company;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.CompanyDetails;
import co.com.cybersoft.maintenance.tables.events.company.CreateCompanyEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyPageEvent;
import co.com.cybersoft.maintenance.tables.events.company.CompanyModificationEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.company.RequestCompanyPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Company;
import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.company.CompanyCustomRepo;
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
public class CompanyPersistenceServiceImpl implements CompanyPersistenceService{

	private final CompanyRepository companyRepository;
	
	private final CompanyCustomRepo companyCustomRepo;
	
	
	public CompanyPersistenceServiceImpl(final CompanyRepository companyRepository, final CompanyCustomRepo companyCustomRepo) {
		this.companyRepository=companyRepository;
		this.companyCustomRepo=companyCustomRepo;
	}
	
	public CompanyDetailsEvent createCompany(CreateCompanyEvent event) throws Exception{
		Company company = new Company().fromCompanyDetails(event.getCompanyDetails());
		company = companyRepository.save(company);
		company = companyRepository.findOne(company.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",company.getId());
		return new CompanyDetailsEvent(new CompanyDetails().toCompanyDetails(company));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"company", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public CompanyPageEvent requestCompanyPage(RequestCompanyPageEvent event) throws Exception {
		Page<Company> companys = companyRepository.findAll(event.getPageable());
		return new CompanyPageEvent(companys);
	}

	public CompanyDetailsEvent requestCompanyDetails(RequestCompanyDetailsEvent event) throws Exception {
		CompanyDetails companyDetails=null;
		if (event.getId()!=null){
			Company company = companyRepository.findOne(event.getId());
			if (company!=null)
				companyDetails = new CompanyDetails().toCompanyDetails(company);
		}
		return new CompanyDetailsEvent(companyDetails);
		
	}

	public CompanyDetailsEvent modifyCompany(CompanyModificationEvent event) throws Exception {
		Company company = new Company().fromCompanyDetails(event.getCompanyDetails());
		company = companyRepository.save(company);
		company = companyRepository.findOne(company.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",company.getId());
		return new CompanyDetailsEvent(new CompanyDetails().toCompanyDetails(company));
	}
	
		public CompanyDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Company> all = companyRepository.findAll();
			CompanyDetails single = new CompanyDetails();
			for (Company company : all) {
				single=new CompanyDetails().toCompanyDetails(company);
				break;
			}
			return new CompanyDetailsEvent(single);
		}
	
	public CompanyPageEvent requestAllByCodeNit(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByCodeNit(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByCompanyName(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByCompanyName(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByCompanyLicense(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByCompanyLicense(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByComment(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByComment(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByUserLoginCompany(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByUserLoginCompany(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByUserSendHistory(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByUserSendHistory(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByStateShippingHistory(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByStateShippingHistory(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByStateCompany(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByStateCompany(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByCodeOfWIN(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByCodeOfWIN(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByInventoryConditionPermanent(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByInventoryConditionPermanent(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}public CompanyPageEvent requestAllByPermanentInventoryWorkOrder(EmbeddedField... fields) throws Exception {
			List<Company> all = companyCustomRepo.findAllActiveByPermanentInventoryWorkOrder(fields);
			List<CompanyDetails> list = new ArrayList<CompanyDetails>();
			for (Company company : all) {
				list.add(new CompanyDetails().toCompanyDetails(company, fields));
			}
			return new CompanyPageEvent(list);
		}
	

	public CompanyPageEvent requestCompanyFilterPage(RequestCompanyPageEvent event) throws Exception {
		Page<Company> page = companyCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new CompanyPageEvent(page, companyCustomRepo.getTotalCount());
	}
	
	public CompanyPageEvent requestCompanyFilter(
			RequestCompanyPageEvent event) throws Exception {
		List<Company> all = companyCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		CompanyPageEvent pageEvent = new CompanyPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}