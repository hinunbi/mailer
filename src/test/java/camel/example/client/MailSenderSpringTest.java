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
		// 메일 발신 수신 정보 입력
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("To", "mailer@barunmo.net");
		header.put("From", "hinunbi@barunmo.com");
		header.put("Subject", "Camel MailSender Test");
		// 메일 본문 템플릿 개체 값 정보 입력
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "바른모");
		mailSender.send(header, model);
		// ...
		// 메일이 도착 했는지 수신지 메일을 확인하다.
	}
}
