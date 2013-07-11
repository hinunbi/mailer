package camel.example.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MailSenderTest {

	MailSender mailSender;
	CamelContext context;

	@Before
	public void setUp() throws Exception {
		// ī�� ���ؽ�Ʈ ����
		context = new DefaultCamelContext();
		context.disableJMX();
		// �޽��� ����� �ʱ�ȭ (���� ���� ���� ���� ����)
		MailSenderBuilder mailSenderBuilder = new MailSenderBuilder();
		mailSenderBuilder.setHost("192.168.1.65");
		mailSenderBuilder.setPort(25);
		mailSenderBuilder.setUsername("mailer");
		mailSenderBuilder.setPassword("mailer");

		// �޽��� ����� ���
		context.addRoutes(mailSenderBuilder);

		// ī�� ���ؽ�Ʈ ����
		context.start();

		// ������ ���ø� ����, ����
		mailSender = new MailSender();
		mailSender.setProducer(context.createProducerTemplate());
	}

	@After
	public void tearDown() throws Exception {
		// ī�� ���ؽ�Ʈ ����
		context.stop();
	}

	@Test
	public void test() {

		// ���� �߽� ���� ���� �Է�
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("To", "mailer@barunmo.net");
		header.put("From", "hinunbi@barunmo.com");
		header.put("Subject", "Camel MailSender Test");
		// ���� ���� ���ø� ��ü �� ���� �Է�
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "�ٸ���");
		mailSender.send(header, model);
		// ...
		// ������ ���� �ߴ��� ������ ������ Ȯ���ϴ�.
	}
}
