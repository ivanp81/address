package it.joint.address.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.joint.address.client.provider.GetAddress;
import it.joint.address.client.provider.AddressResponse;


@Service
public class AddressClientImpl implements AddressClient {

    private GetAddress addressApi;

    @Autowired
    public AddressClientImpl(final GetAddress addressApi) {
    	
        this.addressApi = addressApi;
    }
    
    public AddressResponse findAddresses(String postCode) {
    	
        return addressApi.find(postCode);
    }
}