package br.com.fabricio.utils;

import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class Resource {

	protected static Connection connection = null;
	protected static Session session = null;
	protected static MessageProducer producer = null;
	protected static MessageConsumer consumer = null;

	
	public static void closeResources() {
		if (consumer != null) {
			consumer = null;
		}

		if (producer != null) {
			producer = null;
		}

		if (session != null) {
			session = null;
		}

		if (connection != null) {
			connection = null;
		}
	}

}
