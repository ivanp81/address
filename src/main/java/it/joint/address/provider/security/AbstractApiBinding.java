package it.joint.address.provider.security;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

public abstract class AbstractApiBinding {

    private RestTemplate restTemplate;

    protected AbstractApiBinding(String apiUrl, String apiKey) {

	restTemplate = createRestTemplate(apiUrl, apiKey);
    }

    private RestTemplate createRestTemplate(String apiUrl, String apiKey) {

	RestTemplate client = new RestTemplate();
	client.setUriTemplateHandler(configureUriTemplateHandlerWithApiUrlAsBaseUrl(apiUrl));
	client.setInterceptors(addSecurityInterceptor(apiKey));
	return client;
    }

    private DefaultUriTemplateHandler configureUriTemplateHandlerWithApiUrlAsBaseUrl(String apiUrl) {
	DefaultUriTemplateHandler uriTemplate = new DefaultUriTemplateHandler();
	uriTemplate.setBaseUrl(apiUrl);
	return uriTemplate;
    }

    private List<ClientHttpRequestInterceptor> addSecurityInterceptor(String apiKey) {

	List<ClientHttpRequestInterceptor> interceptors = new LinkedList<ClientHttpRequestInterceptor>();
	ClientHttpRequestInterceptor securityInterceptor = new ApiKeyParameterRequestInterceptor(apiKey);
	interceptors.add(securityInterceptor);
	return interceptors;
    }

    public RestTemplate getRestTemplate() {
	return restTemplate;
    }
}
