package it.joint.address.integrationTests.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressClientStubTest {

	@Autowired
	AddressClient addressClient;

	String validPostCode = "XX200X";
	
	AddressResponse expectedResponse;
    
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9000);

	@Before
    public void setUp() throws Exception {
		expectedResponse = new AddressResponse.Builder().withLatitude("12345").withLongitude("12345").build();
    }
	
	@Test
	public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

		wireMockRule.stubFor(get(urlPathEqualTo("/find/" + validPostCode))
			.willReturn(aResponse()
					.withBody(expectedResponse.toJsonString())
					.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.withStatus(200)));

		AddressResponse actualResponse = addressClient.findAddresses(validPostCode);
		
		assertThat(actualResponse, equalTo(expectedResponse));
	}
}