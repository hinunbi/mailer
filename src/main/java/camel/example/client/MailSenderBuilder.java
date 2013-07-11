package camel.example.client;

import org.apache.camel.builder.RouteBuilder;

public class MailSenderBuilder extends RouteBuilder {

	private String host;
	private Integer port = 25;
	private String username;
	private String password;

	@Override
	public void configure() throws Exception {

		from("direct:sender").
				routeId("mailSendRoute").
				to("velocity:camel/example/client/template/letter.vm").
				to(String.format("smtp://%s@%s:%d?password=%s&contentType=text/html",
						username, host, port, password));
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
