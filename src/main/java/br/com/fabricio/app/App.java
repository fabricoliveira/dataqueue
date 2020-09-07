package br.com.fabricio.app;

import javax.jms.JMSException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.fabricio.jobs.PublisherJob;
import br.com.fabricio.services.ConsumerService;
import br.com.fabricio.utils.Resource;

public class App {

	public static void main(String[] args) {
		try {
			new ConsumerService().consume();
			
			SchedulerFactory shedulerFactory = new StdSchedulerFactory();
			JobDetail job = JobBuilder.newJob(PublisherJob.class).build();
			
			CronTrigger trigger = TriggerBuilder.newTrigger()
												.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
												.build();
			
			Scheduler scheduler = shedulerFactory.getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException | JMSException e) {
			e.printStackTrace();
		} finally {
			Resource.closeResources();
		}
	}

}
