package co.com.cybersoft.man.services.timer;

import org.quartz.JobDetail;

public interface TimerService {
	void scheduleFirstThingDailyJob(JobDetail job) throws Exception;
}
