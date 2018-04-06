package it.joint.address.client;

import it.joint.address.provider.AddressResponse;

public interface AddressClient {

    public AddressResponse findAddresses(String postCode);
}