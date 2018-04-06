package it.joint.address.integrationTests.client;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class AddressClientTest {

	@Autowired
	 AddressClient addressClient;
	
	String validPostCode = "XX200X";
	
	AddressResponse expectedResponse;
    
	@Before
    public void setUp() throws Exception {
		expectedResponse = new AddressResponse.Builder().withLatitude("12345").withLongitude("12345").build();
    }
	
	@Test
	public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

		String validPostCode = "XX200X";
		
		AddressResponse actualResponse = addressClient.findAddresses(validPostCode);

		assertThat(actualResponse, is(expectedResponse));
	}
}
