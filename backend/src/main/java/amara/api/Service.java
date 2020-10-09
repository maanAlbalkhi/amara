package amara.api;


import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class Service extends SpringBootServletInitializer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
	
//	@ConfigurationProperties("database")
	public static void main(final String[] args) {
//		try {
//			Logging.info("SERVICE IS STARTING");
//		} catch (IOException e) {
//			System.out.println(e);
//		}
		SpringApplication.run(Service.class, args);
	}
	
	@PreDestroy
	public void onDestroy() throws Exception{
		// ConnectionManager.closeConnections();
		// Logging.info("SERVICE IS SHUTING DOWN");
	}
	
	// https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html
	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
//		try {
			//factory.setPort(Config.getInt("app.port"));
			factory.setPort(9999);
//		} catch (NumberFormatException e) {
//			System.out.println(e);
//		} catch (IOException e) {
//			System.out.println(e);
//		}
	}
}
