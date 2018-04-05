package it.joint.address.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.joint.address.client.provider.AddressApi;
import it.joint.address.client.provider.AddressResponse;


@Service
public class AddressClientImpl implements AddressClient {

    private AddressApi addressApi;

    @Autowired
    public AddressClientImpl(final AddressApi addressApi) {
    	
        this.addressApi = addressApi;
    }
    
    public AddressResponse findAddresses(String postCode) {
    	
        return addressApi.find(postCode);
    }
}