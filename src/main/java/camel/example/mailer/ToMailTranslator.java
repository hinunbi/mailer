package camel.example.mailer;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class ToMailTranslator implements Processor {
	private final String MAIL_ID = "MailID";
	private final String FROM_ADDRESS = "FromAddress";
	private final String TO_ADDRESS = "ToAddress";
	private final String SUBJECT = "Subject";
	private final String BODY = "Body";

	public void process(Exchange exchange) throws Exception {

		Message in = exchange.getIn();

		Map requestMail = in.getBody(Map.class);
		in.removeHeaders("*");
		exchange.setProperty("MailID", requestMail.get(MAIL_ID));
		in.setHeader("From", requestMail.get(FROM_ADDRESS));
		in.setHeader("To", requestMail.get(TO_ADDRESS));
		in.setHeader("Subject", requestMail.get(SUBJECT));
		in.setBody(requestMail.get(BODY));
	}
}
