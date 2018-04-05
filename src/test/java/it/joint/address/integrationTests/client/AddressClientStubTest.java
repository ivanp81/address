package it.joint.address.integrationTests.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import it.joint.address.BaseTest;
import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressClientStubTest extends BaseTest {

	@Autowired
	private AddressClient addressClient;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9000);

	@Test
	public void givenValidPostCode_whenFindAddresses_thenActualResponseIsExpectedResponse() throws Exception {

		String validPostCode = "XX200X";
		
		String findUrl = UriComponentsBuilder
						 .fromPath("/find/{postcode}")
		         	     .buildAndExpand(validPostCode)
		         		 .toString();
		
		wireMockRule.stubFor(get(urlPathEqualTo(findUrl))
					.willReturn(aResponse()
								.withBody(expectedResponse.toJsonString())
								.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
								.withStatus(200)));

		AddressResponse actualResponse = addressClient.findAddresses(validPostCode);
		
		assertThat(actualResponse, is(expectedResponse));
	}
}