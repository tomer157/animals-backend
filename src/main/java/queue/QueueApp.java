package queue;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.thomas.findlocation.entities.RescueTuple;

public class QueueApp {
	public static void main(String[] args) throws NamingException {

	}

	public static void setQueue(RescueTuple t) throws NamingException {
		InitialContext initialContext = new InitialContext();
		Topic topic = (Topic) initialContext.lookup("topic/empTopic");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {

			jmsContext.createProducer().send(topic, t);
		}

		System.out.println("message sent");

	}

}
