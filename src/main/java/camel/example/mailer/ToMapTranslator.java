package camel.example.mailer;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class ToMapTranslator implements Processor {

	private final String MAIL_ID = "MailID";

	public void process(Exchange exchange) throws Exception {

		Message in = exchange.getIn();

		String mailID = exchange.getProperty(MAIL_ID, String.class);
		HashMap<String, String> requestResult = new HashMap<String, String>();
		requestResult.put(MAIL_ID, mailID);
		in.setBody(requestResult);
	}
}
