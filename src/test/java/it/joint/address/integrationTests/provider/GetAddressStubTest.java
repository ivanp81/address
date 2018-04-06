package it.joint.address.integrationTests.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.GetAddress;

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
public class GetAddressStubTest {

	@Autowired
	private GetAddress getAddress;

	private String validPostCode = "XX200X";
	
	private AddressResponse expectedResponse;
    
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9000);

	@Before
    public void setUp() {
		expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078).withLongitude(-0.1320359706878662).build();
    }
	
	@Test
	public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

		wireMockRule.stubFor(get(urlPathEqualTo("/find/" + validPostCode))
			.willReturn(aResponse()
					.withBody(expectedResponse.toJsonString())
					.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.withStatus(200)));

		AddressResponse actualResponse = getAddress.find(validPostCode);
		
		assertThat(actualResponse, equalTo(expectedResponse));
	}
}