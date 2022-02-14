package perscholas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import perscholas.webservices.CountryClient;

@Configuration
public class WebServiceConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("perscholas.webservices.consumer");
		return marshaller;
	}

	@Bean
	public CountryClient countryClient(Jaxb2Marshaller marshaller) {
		CountryClient client = new CountryClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
