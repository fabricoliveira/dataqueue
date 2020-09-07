package br.com.fabricio.jobs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.jms.JMSException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.fabricio.models.InputDTO;
import br.com.fabricio.services.FileService;
import br.com.fabricio.services.PublisherService;
import br.com.fabricio.utils.PathUtils;
import br.com.fabricio.utils.Resource;

@DisallowConcurrentExecution
public class PublisherJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("PublisherJob is scanning files.");
			PublisherService publisherService = new PublisherService();
			FileService fileService = new FileService(PathUtils.getInputDirectory());
			Set<String> filenames = new HashSet<>(fileService.getFilenames());

			for (String filename : filenames) {
				InputDTO inputDTO = fileService.readFileFromDirectory(filename);
				if(inputDTO != null) {
					publisherService.publish(PathUtils.getQueueName(), inputDTO);					
					fileService.deleteFile(filename);
				}
			}
		} catch (JMSException | IOException e) {
			e.printStackTrace();
		} finally {
			Resource.closeResources();
		}
	}

}
