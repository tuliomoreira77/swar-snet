package br.com.swar.snet;

import java.util.TimeZone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "br.com.swar.snet")
public class SwarSnetMainApplication extends SpringBootServletInitializer implements InitializingBean {
	
	public static void main(String[] args) {
		SpringApplication.run(SwarSnetMainApplication.class, args);
	}

	public void afterPropertiesSet() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}
	
}
