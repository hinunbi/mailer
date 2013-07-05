package camel.example.mailer;

import com.brm.pattern.configurer.Configurer;
import org.apache.camel.CamelContext;
import org.apache.camel.component.mail.MailComponent;
import org.apache.camel.component.mybatis.MyBatisComponent;
import org.apache.camel.component.properties.PropertiesComponent;

public class MailerConfigurer implements Configurer<CamelContext> {

	private String sqlMapConfgFileUri = "classpath:camel/example/mailer/data/SqlMapConfig.xml";
	private String propertiesFile = "mailer.properties";

	private PropertiesComponent propertiesComponent;
	private MyBatisComponent myBatisComponent;
	private MailComponent mailComponent;

	public void configure(CamelContext context) throws Exception {

		// propery 컴포넌트 추가
		if (propertiesComponent == null) {
			propertiesComponent = new PropertiesComponent();
			propertiesComponent.setLocation(propertiesFile);
		}
		context.addComponent("properties", propertiesComponent);

		// MyBatis 컴포넌트 추가
		if (myBatisComponent == null) {
			myBatisComponent = new MyBatisComponent();
			myBatisComponent.setConfigurationUri(sqlMapConfgFileUri);
		}
		context.addComponent("mybatis", myBatisComponent);

		// smtp 컴포넌트 추가
		if (mailComponent == null) {
			mailComponent = new MailComponent();
		}
		context.addComponent("smtp", mailComponent);

	}

	public void setPropertiesComponent(PropertiesComponent propertiesComponent) {
		this.propertiesComponent = propertiesComponent;
	}

	public void setMyBatisComponent(MyBatisComponent myBatisComponent) {
		this.myBatisComponent = myBatisComponent;
	}

	public void setMailComponent(MailComponent mailComponent) {
		this.mailComponent = mailComponent;
	}
}
