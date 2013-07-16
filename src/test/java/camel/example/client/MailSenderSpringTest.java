package camel.example.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/camel/example/client/MailSender.xml" })
public class MailSenderSpringTest {

	@Autowired
	MailSender mailSender;

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
