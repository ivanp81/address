package it.joint.address.unitTests.client.provider.template;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import it.joint.address.client.provider.AddressApi;
import it.joint.address.client.provider.AddressResponse;
import it.joint.address.client.provider.template.AddressApiTemplate;

@RunWith(SpringRunner.class)
public class AddressApiTemplateTest {
    
	private AddressApi addressApi;
    private AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
    	addressApi = mock(AddressApiTemplate.class);
        expectedResponse = mock(AddressResponse.class);
    }
    
    @Test
    public void givenValidPostCode_whenFind_thenActualResponseEqualToExpectedResponse() throws Exception {

    	String validPostCode = "XX200X";

        doReturn(expectedResponse)
        .when(addressApi)
        .find(validPostCode);
        
        AddressResponse actualResponse = addressApi.find(validPostCode);    
        
        assertThat(actualResponse, equalTo(expectedResponse));
    }
}