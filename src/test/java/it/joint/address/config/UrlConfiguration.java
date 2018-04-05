package it.joint.address.config;

import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class UrlConfiguration {
	
	private static final String ADDRESS_URL = System.getProperty("acceptance.address.url", "http://localhost:8080");
	private static final String LOCALHOST_URL = "http://localhost";
	
	@Bean
	public UriComponentsBuilder addressUrl() throws URISyntaxException {
		return UriComponentsBuilder.fromUriString(ADDRESS_URL);
	}
	
	@Bean
	public UriComponentsBuilder localHostUrl() throws URISyntaxException {
		return UriComponentsBuilder.fromUriString(LOCALHOST_URL);
	}
}
