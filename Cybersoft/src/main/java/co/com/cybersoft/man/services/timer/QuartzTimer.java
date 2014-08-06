package co.com.cybersoft.man.services.timer;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Schedules a Quartz Job to be executed first thing
 * in the morning on a daily basis
 * @author Daniel
 *
 */
public class QuartzTimer implements TimerService{

	@Override
	public void scheduleFirstThingDailyJob(JobDetail job) throws Exception{
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		DateTime dateTime = new DateTime(cal.getTimeInMillis());
		DateTime dt = dateTime.plusDays(1).withHourOfDay(1).withMinuteOfHour(0);
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24).repeatForever())
				.startAt(dt.toDate()).build();
		
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		
	}

}
