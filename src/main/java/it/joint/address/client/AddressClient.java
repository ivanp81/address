package it.joint.address.client;

import it.joint.address.client.provider.AddressResponse;

public interface AddressClient {

    public AddressResponse findAddresses(String postCode);
}