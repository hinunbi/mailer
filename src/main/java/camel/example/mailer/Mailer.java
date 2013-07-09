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

		// 카멜 컨텍스트 생성
		DefaultCamelContext context = new DefaultCamelContext();

		// 컴포넌트 등록
		if (configurer == null) {
			// 메일러 설정자 생성
			setConfigurer(new MailerConfigurer());
		}
		configurer.configure(context);

		// 라우팅 추가
		if (builder == null) {
			// 심플 메일러 라우팅 생성
			setBuilder(new SimpleMailerBuilder());
		}
		context.addRoutes(builder);

		// 메일러 시작
		logger.info("Mailer start...");
		context.start();

		// 메일러 라우팅 기동 호출
		ProducerTemplate producer = context.createProducerTemplate();
		producer.requestBody("direct:start", new HashMap());

		// 메일러 완료
		logger.info("Mailer done.");
		// 카멜 컨텍스트 종료
		context.stop();

	}

	public void setConfigurer(MailerConfigurer configurer) {
		this.configurer = configurer;
	}

	public void setBuilder(RouteBuilder builder) {
		this.builder = builder;
	}
}
