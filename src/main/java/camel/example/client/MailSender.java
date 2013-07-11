package camel.example.client;

import java.util.Map;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service("mailSender")
public class MailSender {

	@Produce
	private ProducerTemplate producer;

	public void send(Map<String, Object> header, Map<String, Object> model) {
		producer.requestBodyAndHeaders("direct:sender", model, header);
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}
}
