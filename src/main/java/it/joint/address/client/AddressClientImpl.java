package it.joint.address.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.GetAddress;


@Service
public class AddressClientImpl implements AddressClient {

    private GetAddress getAddress;

    @Autowired
    public AddressClientImpl(final GetAddress getAddress) {
    	
        this.getAddress = getAddress;
    }
    
    public AddressResponse findAddresses(String postCode) {
    	
        return getAddress.find(postCode);
    }
}