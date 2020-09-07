package br.com.fabricio.services;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import br.com.fabricio.models.InputDTO;
import br.com.fabricio.utils.ConnectionUtils;
import br.com.fabricio.utils.PathUtils;
import br.com.fabricio.utils.Resource;

public class PublisherService extends Resource {
	
	public void publish(String queueName, InputDTO inputDTO) throws JMSException {
		connection = ConnectionUtils.getInstance().getConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(queueName);

		String json = new Gson().toJson(inputDTO);
		TextMessage textMessage = session.createTextMessage(json);

		producer = session.createProducer(destination);
		producer.send(textMessage);
		
		System.out.println(String.format("PublisherService.publish  ->  queued \"%s\" to \"%s\"", inputDTO.getFilename(), PathUtils.getQueueName()));
	}
	
}
