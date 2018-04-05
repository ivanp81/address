package it.joint.address.unitTests.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import it.joint.address.api.AddressController;
import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddressControllerTest {

	private AddressController addressController;
   
    @Mock
    private AddressClient addressClient;
    
    private AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
        initMocks(this);
        addressController = new AddressController(addressClient);
        expectedResponse = mock(AddressResponse.class);
    }
    
    @Test
    public void givenValidPostCode_whenFindAddresses_thenActualResponseEqualToExpectedResponse() throws Exception {

        String validPostCode = "XX200X";
        
        doReturn(expectedResponse)
        .when(addressClient)
        .findAddresses(validPostCode);
        
        AddressResponse actualResponse = addressController.findAddresses(validPostCode);
        
        assertThat(actualResponse, equalTo(expectedResponse));
    }
}