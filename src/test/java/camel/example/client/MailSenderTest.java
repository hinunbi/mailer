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
		// 카멜 컨텍스트 생성
		context = new DefaultCamelContext();
		context.disableJMX();
		// 메시지 라우팅 초기화 (메일 서버 접속 정보 주입)
		MailSenderBuilder mailSenderBuilder = new MailSenderBuilder();
		mailSenderBuilder.setHost("192.168.1.65");
		mailSenderBuilder.setPort(25);
		mailSenderBuilder.setUsername("mailer");
		mailSenderBuilder.setPassword("mailer");

		// 메시지 라우팅 등록
		context.addRoutes(mailSenderBuilder);

		// 카멜 컨텍스트 시작
		context.start();

		// 생산자 템플릿 생성, 주입
		mailSender = new MailSender();
		mailSender.setProducer(context.createProducerTemplate());
	}

	@After
	public void tearDown() throws Exception {
		// 카멜 컨텍스트 종료
		context.stop();
	}

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
