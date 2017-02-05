package co.com.cybersoft.maintenance.tables.persistence.services.jobThird;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.JobThirdDetails;
import co.com.cybersoft.maintenance.tables.events.jobThird.CreateJobThirdEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.JobThirdModificationEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.jobThird.RequestJobThirdPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.JobThird;
import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.jobThird.JobThirdCustomRepo;
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
public class JobThirdPersistenceServiceImpl implements JobThirdPersistenceService{

	private final JobThirdRepository jobThirdRepository;
	
	private final JobThirdCustomRepo jobThirdCustomRepo;
	
	
	public JobThirdPersistenceServiceImpl(final JobThirdRepository jobThirdRepository, final JobThirdCustomRepo jobThirdCustomRepo) {
		this.jobThirdRepository=jobThirdRepository;
		this.jobThirdCustomRepo=jobThirdCustomRepo;
	}
	
	public JobThirdDetailsEvent createJobThird(CreateJobThirdEvent event) throws Exception{
		JobThird jobThird = new JobThird().fromJobThirdDetails(event.getJobThirdDetails());
		jobThird = jobThirdRepository.save(jobThird);
		jobThird = jobThirdRepository.findOne(jobThird.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",jobThird.getId());
		return new JobThirdDetailsEvent(new JobThirdDetails().toJobThirdDetails(jobThird));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"jobThird", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public JobThirdPageEvent requestJobThirdPage(RequestJobThirdPageEvent event) throws Exception {
		Page<JobThird> jobThirds = jobThirdRepository.findAll(event.getPageable());
		return new JobThirdPageEvent(jobThirds);
	}

	public JobThirdDetailsEvent requestJobThirdDetails(RequestJobThirdDetailsEvent event) throws Exception {
		JobThirdDetails jobThirdDetails=null;
		if (event.getId()!=null){
			JobThird jobThird = jobThirdRepository.findOne(event.getId());
			if (jobThird!=null)
				jobThirdDetails = new JobThirdDetails().toJobThirdDetails(jobThird);
		}
		return new JobThirdDetailsEvent(jobThirdDetails);
		
	}

	public JobThirdDetailsEvent modifyJobThird(JobThirdModificationEvent event) throws Exception {
		JobThird jobThird = new JobThird().fromJobThirdDetails(event.getJobThirdDetails());
		jobThird = jobThirdRepository.save(jobThird);
		jobThird = jobThirdRepository.findOne(jobThird.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",jobThird.getId());
		return new JobThirdDetailsEvent(new JobThirdDetails().toJobThirdDetails(jobThird));
	}
	
		public JobThirdDetailsEvent getOnlyRecord() throws Exception {
			Iterable<JobThird> all = jobThirdRepository.findAll();
			JobThirdDetails single = new JobThirdDetails();
			for (JobThird jobThird : all) {
				single=new JobThirdDetails().toJobThirdDetails(jobThird);
				break;
			}
			return new JobThirdDetailsEvent(single);
		}
	
	public JobThirdPageEvent requestAllByJob(EmbeddedField... fields) throws Exception {
			List<JobThird> all = jobThirdCustomRepo.findAllActiveByJob(fields);
			List<JobThirdDetails> list = new ArrayList<JobThirdDetails>();
			for (JobThird jobThird : all) {
				list.add(new JobThirdDetails().toJobThirdDetails(jobThird, fields));
			}
			return new JobThirdPageEvent(list);
		}public JobThirdPageEvent requestAllByThird(EmbeddedField... fields) throws Exception {
			List<JobThird> all = jobThirdCustomRepo.findAllActiveByThird(fields);
			List<JobThirdDetails> list = new ArrayList<JobThirdDetails>();
			for (JobThird jobThird : all) {
				list.add(new JobThirdDetails().toJobThirdDetails(jobThird, fields));
			}
			return new JobThirdPageEvent(list);
		}
	

	public JobThirdPageEvent requestJobThirdFilterPage(RequestJobThirdPageEvent event) throws Exception {
		Page<JobThird> page = jobThirdCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new JobThirdPageEvent(page, jobThirdCustomRepo.getTotalCount());
	}
	
	public JobThirdPageEvent requestJobThirdFilter(
			RequestJobThirdPageEvent event) throws Exception {
		List<JobThird> all = jobThirdCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		JobThirdPageEvent pageEvent = new JobThirdPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}