package it.joint.address.provider.support;

import java.net.URI;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpRequestDecorator extends HttpRequestWrapper {

    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

    public HttpRequestDecorator(HttpRequest request) {
	super(request);
    }

    public void addParameter(String name, String value) {
	parameters.add(name, value);
    }

    @Override
    public URI getURI() {
	if (parameters.isEmpty()) {
	    return super.getURI();
	}
	return UriComponentsBuilder.fromUri(super.getURI()).queryParams(parameters).build().toUri();
    }

}