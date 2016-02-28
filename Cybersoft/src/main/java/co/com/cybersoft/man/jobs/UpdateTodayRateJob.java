package co.com.cybersoft.man.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import co.com.cybersoft.man.services.currency.CurrencyUpdateService;

public class UpdateTodayRateJob implements Job{

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			CurrencyUpdateService currencyUpdateService = (CurrencyUpdateService) context.getJobDetail().getJobDataMap().get("currencyUpdateService");
			currencyUpdateService.updateTodayRates();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
