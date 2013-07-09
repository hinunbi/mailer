package camel.example.mailer;

import java.util.HashMap;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mailer {

	private static final Logger logger = LoggerFactory.getLogger(Mailer.class);

	MailerConfigurer configurer;
	RouteBuilder builder;

	public void run() throws Exception {

		// ī�� ���ؽ�Ʈ ����
		DefaultCamelContext context = new DefaultCamelContext();

		// ������Ʈ ���
		if (configurer == null) {
			// ���Ϸ� ������ ����
			setConfigurer(new MailerConfigurer());
		}
		configurer.configure(context);

		// ����� �߰�
		if (builder == null) {
			// ���� ���Ϸ� ����� ����
			setBuilder(new SimpleMailerBuilder());
		}
		context.addRoutes(builder);

		// ���Ϸ� ����
		logger.info("Mailer start...");
		context.start();

		// ���Ϸ� ����� �⵿ ȣ��
		ProducerTemplate producer = context.createProducerTemplate();
		producer.requestBody("direct:start", new HashMap());

		// ���Ϸ� �Ϸ�
		logger.info("Mailer done.");
		// ī�� ���ؽ�Ʈ ����
		context.stop();

	}

	public void setConfigurer(MailerConfigurer configurer) {
		this.configurer = configurer;
	}

	public void setBuilder(RouteBuilder builder) {
		this.builder = builder;
	}
}
