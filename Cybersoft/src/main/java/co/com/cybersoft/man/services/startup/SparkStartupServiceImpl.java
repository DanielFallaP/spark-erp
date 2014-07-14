package co.com.cybersoft.man.services.startup;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.man.jobs.UpdateTodayRateJob;
import co.com.cybersoft.man.services.currency.CurrencyUpdateService;
import co.com.cybersoft.man.services.sequence.SequenceService;
import co.com.cybersoft.man.services.tenancy.TenantConfigurationService;
import co.com.cybersoft.man.services.timer.TimerService;

public class SparkStartupServiceImpl implements SparkStartupService{

	@Autowired
	CurrencyUpdateService currencyUpdateService;
	
	@Autowired
	TimerService timerService;

	@Autowired
	private TenantConfigurationService tenantConfigService;
	
	@Autowired
	private SequenceService sequenceStartupService; 

	@PostConstruct
	@Override
	public void bootSPARK() throws Exception {
		bootTenantConfig();
		bootCurrencyConfig();
		bootSequences();
	}

	private void bootSequences() throws Exception{
		sequenceStartupService.startupSequences();
	}

	private void bootCurrencyConfig() throws Exception{
	
		Date to=new Date();
		GregorianCalendar init = new GregorianCalendar();
		init.setTime(to);
		init.add(Calendar.DATE, -30);
	
		//Update last days rates
		currencyUpdateService.updatePeriodRates(init.getTime(), to);
		
		Map<String,CurrencyUpdateService> map = new HashMap<String, CurrencyUpdateService>();
		map.put("currencyUpdateService", currencyUpdateService);
		JobDetail job = JobBuilder.newJob(UpdateTodayRateJob.class)
				.withIdentity("testJob").usingJobData(new JobDataMap(map))
				.build();

		
		//Schedule daily jobs for continuous updates
		timerService.scheduleFirstThingDailyJob(job);
	}
	
	private void bootTenantConfig() throws Exception {
		tenantConfigService.updateTenantConfig();
	}
	

}
