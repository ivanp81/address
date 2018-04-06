package it.joint.address.acceptanceTests.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcceptanceTestsConfiguration {
	
	private static final String ADDRESS_URL = System.getProperty("acceptance.address.url", "http://localhost:8080");
	
	@Bean
	public URI baseUri() throws URISyntaxException {
		return new URI(ADDRESS_URL);
	}
}
