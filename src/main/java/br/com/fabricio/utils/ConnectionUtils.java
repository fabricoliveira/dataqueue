package br.com.fabricio.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConnectionUtils extends Resource {

	private static ConnectionUtils instance;
	private static ConnectionFactory factory = null;
	private final static String username = "admin";
	private final static String password = "admin";
	private final static String brokerUrl = "tcp://localhost:61616";
	
	
	private ConnectionUtils() throws JMSException {
		factory = new ActiveMQConnectionFactory(username, password, brokerUrl);
		connection = factory.createConnection();
	}

	
	public Connection getConnection() throws JMSException {
		return connection;
	}

	
	public static ConnectionUtils getInstance() throws JMSException {
		if (instance == null || isClosed(instance.getConnection())) {
			instance = new ConnectionUtils();
		}
		return instance;
	}

	
	private static boolean isClosed(Connection connection) {
		if (connection == null) {
			return true;
		}
		return false;
	}

}
