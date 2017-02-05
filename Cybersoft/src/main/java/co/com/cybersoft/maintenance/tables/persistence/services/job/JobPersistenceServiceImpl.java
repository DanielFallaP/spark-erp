package co.com.cybersoft.maintenance.tables.persistence.services.job;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.domain.JobDetails;
import co.com.cybersoft.maintenance.tables.events.job.CreateJobEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobPageEvent;
import co.com.cybersoft.maintenance.tables.events.job.JobModificationEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobDetailsEvent;
import co.com.cybersoft.maintenance.tables.events.job.RequestJobPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.Job;
import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.job.JobCustomRepo;
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
public class JobPersistenceServiceImpl implements JobPersistenceService{

	private final JobRepository jobRepository;
	
	private final JobCustomRepo jobCustomRepo;
	
	
	public JobPersistenceServiceImpl(final JobRepository jobRepository, final JobCustomRepo jobCustomRepo) {
		this.jobRepository=jobRepository;
		this.jobCustomRepo=jobCustomRepo;
	}
	
	public JobDetailsEvent createJob(CreateJobEvent event) throws Exception{
		Job job = new Job().fromJobDetails(event.getJobDetails());
		job = jobRepository.save(job);
		job = jobRepository.findOne(job.getId());
		
		//updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"creation",job.getId());
		return new JobDetailsEvent(new JobDetails().toJobDetails(job));
	}
	
	/*public void updateLog(String userName, String action, String id){
		SessionAction sessionAction = new SessionAction(userName, CyberUtils.userSessions.get(userName), action, new Date(),"job", id);
		new Thread(new SessionLogger(sessionAction, mongo)).start();
	}*/

	public JobPageEvent requestJobPage(RequestJobPageEvent event) throws Exception {
		Page<Job> jobs = jobRepository.findAll(event.getPageable());
		return new JobPageEvent(jobs);
	}

	public JobDetailsEvent requestJobDetails(RequestJobDetailsEvent event) throws Exception {
		JobDetails jobDetails=null;
		if (event.getId()!=null){
			Job job = jobRepository.findOne(event.getId());
			if (job!=null)
				jobDetails = new JobDetails().toJobDetails(job);
		}
		return new JobDetailsEvent(jobDetails);
		
	}

	public JobDetailsEvent modifyJob(JobModificationEvent event) throws Exception {
		Job job = new Job().fromJobDetails(event.getJobDetails());
		job = jobRepository.save(job);
		job = jobRepository.findOne(job.getId());
		
	//	updateLog(SecurityContextHolder.getContext().getAuthentication().getName(),"modification",job.getId());
		return new JobDetailsEvent(new JobDetails().toJobDetails(job));
	}
	
		public JobDetailsEvent getOnlyRecord() throws Exception {
			Iterable<Job> all = jobRepository.findAll();
			JobDetails single = new JobDetails();
			for (Job job : all) {
				single=new JobDetails().toJobDetails(job);
				break;
			}
			return new JobDetailsEvent(single);
		}
	
	public JobPageEvent requestAllByResponsibleCenter(EmbeddedField... fields) throws Exception {
			List<Job> all = jobCustomRepo.findAllActiveByResponsibleCenter(fields);
			List<JobDetails> list = new ArrayList<JobDetails>();
			for (Job job : all) {
				list.add(new JobDetails().toJobDetails(job, fields));
			}
			return new JobPageEvent(list);
		}public JobPageEvent requestAllByNameJob(EmbeddedField... fields) throws Exception {
			List<Job> all = jobCustomRepo.findAllActiveByNameJob(fields);
			List<JobDetails> list = new ArrayList<JobDetails>();
			for (Job job : all) {
				list.add(new JobDetails().toJobDetails(job, fields));
			}
			return new JobPageEvent(list);
		}public JobPageEvent requestAllByTypeWork(EmbeddedField... fields) throws Exception {
			List<Job> all = jobCustomRepo.findAllActiveByTypeWork(fields);
			List<JobDetails> list = new ArrayList<JobDetails>();
			for (Job job : all) {
				list.add(new JobDetails().toJobDetails(job, fields));
			}
			return new JobPageEvent(list);
		}
	

	public JobPageEvent requestJobFilterPage(RequestJobPageEvent event) throws Exception {
		Page<Job> page = jobCustomRepo.findAll(event.getPageable(), event.getFilter());
		return new JobPageEvent(page, jobCustomRepo.getTotalCount());
	}
	
	public JobPageEvent requestJobFilter(
			RequestJobPageEvent event) throws Exception {
		List<Job> all = jobCustomRepo.findAllNoPage(event.getPageable(), event.getFilter());
		JobPageEvent pageEvent = new JobPageEvent();
		pageEvent.setAllList(all);
		return pageEvent;
	}
}