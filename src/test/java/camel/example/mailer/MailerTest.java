package camel.example.mailer;

import org.junit.Before;
import org.junit.Test;
import sun.management.snmp.util.MibLogger;

public class MailerTest {

	Mailer mailer;

	@Before
	public void setUp() throws Exception {
		// ���Ϸ� ����
		mailer = new Mailer();
	}

	@Test
	public void test() throws Exception {
		// ���� ���Ϸ� ����
		mailer.run();
	}
}
