package it.joint.address.client.provider.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import it.joint.address.client.provider.AddressApi;
import it.joint.address.client.provider.AddressResponse;

@Component
public class AddressApiTemplate extends AbstractApiBinding implements AddressApi {
	
	public AddressApiTemplate(@Value("${address-api-provider.url}") final String apiUrl, 
						      @Value("${address-api-provider.key}") final String apiKey) {
		super(apiUrl, apiKey);
	}
	
	public AddressResponse find(String postCode) {
		
    	String url = UriComponentsBuilder.fromPath("/find/{postCode}")
    									 .buildAndExpand(postCode)
				  						 .toString();
    	
        return getRestTemplate().getForObject(url, AddressResponse.class);
    }
}
