package co.com.cybersoft.man.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import co.com.cybersoft.man.services.excel.ReportingService;

public class CleanupDirectoryJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			ReportingService reportingService = (ReportingService) context.getJobDetail().getJobDataMap().get("reportingService");
			reportingService.cleanupExcelDirectory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
