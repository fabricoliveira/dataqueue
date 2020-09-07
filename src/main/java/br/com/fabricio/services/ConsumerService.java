package br.com.fabricio.services;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import br.com.fabricio.business.SaleAnalytics;
import br.com.fabricio.models.OutputDTO;
import br.com.fabricio.utils.ConnectionUtils;
import br.com.fabricio.utils.PathUtils;
import br.com.fabricio.utils.Resource;

public class ConsumerService extends Resource {

	public void consume() throws JMSException {
		connection = ConnectionUtils.getInstance().getConnection();
		connection.start();
		session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue(PathUtils.getQueueName());

		consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				try {
					TextMessage textMessage = (TextMessage) message;
					textMessage.acknowledge();
					
					SaleAnalytics saleAnalytics = new SaleAnalytics(textMessage.getText());
					OutputDTO outputDTO = saleAnalytics.analyzeData();
					
					if(outputDTO != null) {
						FileService fileService = new FileService(PathUtils.getOutputDirectory());
						fileService.writeFileToDirectory(outputDTO);						
					}
				} catch (JMSException | IOException e) {
					e.printStackTrace();
				} finally {
					closeResources();
				}
			}
		});
	}

}
