package it.joint.address.unitTests.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.client.AddressClient;
import it.joint.address.client.AddressClientImpl;
import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.GetAddress;

@RunWith(SpringRunner.class)
public class AddressClientTest {

    AddressClient addressClient;

    @MockBean
    private GetAddress getAddress;

    private String validPostCode = "XX200X";

    private AddressResponse expectedResponse;

    @Before
    public void setUp() {
	initMocks(this);
	addressClient = new AddressClientImpl(getAddress);
	expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078)
		.withLongitude(-0.1320359706878662).build();
    }

    @Test
    public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

	given(getAddress.find(validPostCode)).willReturn(expectedResponse);

	AddressResponse addressResponse = addressClient.findAddresses(validPostCode);

	then(getAddress).should().find(validPostCode);

	assertThat(addressResponse, equalTo(expectedResponse));
    }
}