package it.joint.address.unitTests.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.api.AddressController;
import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class AddressControllerTest {

	private AddressController addressController;
   
    @Mock
    private AddressClient addressClient;
    
    String validPostCode = "XX200X";
	
    private AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
        initMocks(this);
        addressController = new AddressController(addressClient);
        expectedResponse = new AddressResponse.Builder().withLatitude("12345").withLongitude("12345").build();
    }
    
    @Test
    public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

    	given(addressClient.findAddresses(validPostCode))
    		.willReturn(expectedResponse);
	    
        AddressResponse addressResponse = addressController.findAddresses(validPostCode);
        
        then(addressClient)
			.should()
			.findAddresses(validPostCode);
    
        assertThat(addressResponse, equalTo(expectedResponse));
    }
}