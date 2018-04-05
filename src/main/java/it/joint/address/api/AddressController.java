package it.joint.address.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

@RestController
public class AddressController {

	private final AddressClient addressClient;

	@Autowired
	public AddressController(final AddressClient addressClient) {
		this.addressClient = addressClient;
	}

	@RequestMapping(value = "/find/{postCode}", method = RequestMethod.GET)
	public AddressResponse findAddresses(@PathVariable String postCode) {
		return addressClient.findAddresses(postCode);
	}
}