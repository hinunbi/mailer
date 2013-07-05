package camel.example.mailer;

import org.junit.Before;
import org.junit.Test;
import sun.management.snmp.util.MibLogger;

public class MailerTest {

	Mailer mailer;

	@Before
	public void setUp() throws Exception {
		// 메일러 생성
		mailer = new Mailer();
	}

	@Test
	public void test() throws Exception {
		// 심플 메일러 실행
		mailer.run();
	}
}
