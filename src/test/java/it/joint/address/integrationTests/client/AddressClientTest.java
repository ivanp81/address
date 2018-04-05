package it.joint.address.integrationTests.client;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.BaseTest;
import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class AddressClientTest extends BaseTest {

	@Autowired
	private AddressClient addressClient;
	
	@Test
	public void givenValidPostCode_whenFindAddresses_thenActualResponseIsExpectedResponse() throws Exception {

		String validPostCode = "XX200X";
		
		AddressResponse actualResponse = addressClient.findAddresses(validPostCode);

		assertThat(actualResponse, is(expectedResponse));
	}
}
