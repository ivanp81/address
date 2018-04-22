package it.joint.address.provider.security;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import it.joint.address.provider.support.HttpRequestDecorator;

public class ApiKeyParameterRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String parameterName;

    private final String apiKey;

    public ApiKeyParameterRequestInterceptor(String apiKey) {
	this(apiKey, "api-key");
    }

    public ApiKeyParameterRequestInterceptor(String apiKey, String parameterName) {
	this.apiKey = apiKey;
	this.parameterName = parameterName;
    }

    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
	    ClientHttpRequestExecution execution) throws IOException {

	HttpRequestDecorator protectedResourceRequest = new HttpRequestDecorator(request);
	protectedResourceRequest.addParameter(parameterName, apiKey);

	return execution.execute(protectedResourceRequest, body);
    }
}
