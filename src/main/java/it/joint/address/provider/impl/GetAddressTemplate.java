package it.joint.address.provider.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.GetAddress;
import it.joint.address.provider.security.AbstractApiBinding;

@Component
public class GetAddressTemplate extends AbstractApiBinding implements GetAddress {
	
	public GetAddressTemplate(@Value("${address.provider.url}") final String apiUrl, 
						      @Value("${address.provider.key}") final String apiKey) {
		super(apiUrl, apiKey);
	}
	
	public AddressResponse find(String postCode) {
		
    	String url = UriComponentsBuilder.fromPath("/find/{postCode}")
    									 .buildAndExpand(postCode)
				  						 .toString();
    	
        return getRestTemplate().getForObject(url, AddressResponse.class);
    }
}
