package camel.example.mailer;

import java.util.HashMap;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleMailerBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:start").
				// 메시지 엔드포인트
				to("mybatis://mailer.selectEmail?statementType=SelectList").
				// 메시지 필터
				filter(simple("${body.size} > 0")).
				removeHeaders("*").
				// 메시지 분할기
				split(body()).
				// 메시지 변환기
				process(new ToMailTranslator()).
				// 메시지 엔드포인트
				to("smtp://{{smtp.user.name}}@{{smtp.host.address}}?password={{smtp.user.password}}&connectionTimeout=30000").
				// 메시지 변환기
				process(new ToMapTranslator()).
				// 메시지 엔드포인트
				to("mybatis://mailer.updateEmail?statementType=Update");
	}
}
