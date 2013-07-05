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
				// �޽��� ��������Ʈ
				to("mybatis://mailer.selectEmail?statementType=SelectList").
				// �޽��� ����
				filter(simple("${body.size} > 0")).
				removeHeaders("*").
				// �޽��� ���ұ�
				split(body()).
				// �޽��� ��ȯ��
				process(new ToMailTranslator()).
				// �޽��� ��������Ʈ
				to("smtp://{{smtp.user.name}}@{{smtp.host.address}}?password={{smtp.user.password}}&connectionTimeout=30000").
				// �޽��� ��ȯ��
				process(new ToMapTranslator()).
				// �޽��� ��������Ʈ
				to("mybatis://mailer.updateEmail?statementType=Update");
	}
}
