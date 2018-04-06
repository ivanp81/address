package it.joint.address.integrationTests.provider;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.GetAddress;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class GetAddressTest {

	@Autowired
	private GetAddress getAddress;
	
	private String validPostCode = "XX200X";
	
	private AddressResponse expectedResponse;
    
	@Before
    public void setUp() {
		expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078).withLongitude(-0.1320359706878662).build();
    }
	
	@Test
	public void givenValidPostCode_whenFindInGetAddress_thenReturnAddressResponse() throws Exception {
		
		AddressResponse addressResponse = getAddress.find(validPostCode);

		assertThat(addressResponse, equalTo(expectedResponse));
	}
}
