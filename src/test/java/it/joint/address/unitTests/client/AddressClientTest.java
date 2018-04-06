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
import it.joint.address.client.provider.GetAddress;
import it.joint.address.client.provider.AddressResponse;

@RunWith(SpringRunner.class)
public class AddressClientTest {
    
	AddressClient addressClient;
	
	@MockBean
    GetAddress getAddress;
	
	String validPostCode = "XX200X";
	
	AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
    	initMocks(this);
        addressClient = new AddressClientImpl(getAddress);
        expectedResponse = new AddressResponse.Builder().withLatitude("12345").withLongitude("12345").build();
    }
    
    @Test
    public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

        given(getAddress.find(validPostCode))
        	.willReturn(expectedResponse);
    	
        AddressResponse addressResponse = addressClient.findAddresses(validPostCode);
        
        then(getAddress)
    		.should()
    		.find(validPostCode);
        
        assertThat(addressResponse, equalTo(expectedResponse));
    }
}