package it.joint.address.unitTests.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.client.AddressClient;
import it.joint.address.client.AddressClientImpl;
import it.joint.address.client.provider.AddressApi;
import it.joint.address.client.provider.AddressResponse;

@RunWith(SpringRunner.class)
public class AddressClientTest {
    
	private AddressClient addressClient;
    private AddressApi addressApi;
    private AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
        
    	initMocks(this);
    	addressApi = mock(AddressApi.class);
        addressClient = new AddressClientImpl(addressApi);
        expectedResponse = mock(AddressResponse.class);
    }
    
    @Test
    public void givenValidPostCode_whenFindAddresses_thenActualResponseEqualToExpectedResponse() throws Exception {

    	String validPostCode = "XX200X";
    	
        given(addressApi.find(validPostCode))
        .willReturn(expectedResponse);
    	
        AddressResponse actualResponse = addressClient.findAddresses(validPostCode);
        
        assertThat(actualResponse, equalTo(expectedResponse));
    }
}