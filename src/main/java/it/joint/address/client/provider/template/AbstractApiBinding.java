package it.joint.address.client.provider.template;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import it.joint.address.client.provider.template.security.ApiKeyParameterRequestInterceptor;

public abstract class AbstractApiBinding {

	private RestTemplate restTemplate;

	private DefaultUriTemplateHandler baseUriTemplate;
	private List<ClientHttpRequestInterceptor> interceptors;

	protected AbstractApiBinding(String apiUrl, String apiKey) {

		interceptors = new LinkedList<ClientHttpRequestInterceptor>();

		configureBaseUrl(apiUrl);
		configureApiKeyParameterInterceptors(apiKey);
		configureRestTemplate();
	}

	private void configureBaseUrl(String apiUrl) {
		baseUriTemplate = new DefaultUriTemplateHandler();
		baseUriTemplate.setBaseUrl(apiUrl);
	}

	private void configureApiKeyParameterInterceptors(String apiKey) {
		ClientHttpRequestInterceptor securityInterceptor = new ApiKeyParameterRequestInterceptor(apiKey);
		interceptors.add(securityInterceptor);
	}

	private void configureRestTemplate() {
		restTemplate = new RestTemplateBuilder()
						   .uriTemplateHandler(baseUriTemplate)
						   .additionalInterceptors(interceptors)
						   .build();
	}

	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}
}
