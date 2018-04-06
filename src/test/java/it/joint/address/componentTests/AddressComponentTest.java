package it.joint.address.componentTests;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import it.joint.address.client.provider.AddressResponse;
import it.joint.address.util.TestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AddressComponentTest {

	@LocalServerPort
	int port;
    
	@Rule
	public WireMockRule addressApiService = new WireMockRule(9000);

	String validPostCode = "XX200X";

	AddressResponse expectedResponse;
    
	@Before
    public void setUp() throws Exception {
		
		AddressResponse.Builder buildExpectedResponse = new AddressResponse.Builder();
		
		expectedResponse = buildExpectedResponse
						   .withLatitude("51.390205383300781")
			               .withLongitude("-0.13203597068786621")
			               .withAddresses(Arrays.asList(new String[] { "10 Watkin Terrace, , , , , Northampton, Northamptonshire"}))
			               .build();
    }
	
	@Test
	public void givenValidPostCode_whenGetFind_thenReturnAddressResponse() throws Exception {
		
		addressApiService.stubFor(get(urlPathEqualTo("/find/" + validPostCode))
			.willReturn(aResponse()
			.withBody(TestUtil.fileAsJson("classpath:addressResponse.json"))
			.withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.withStatus(200)));

		
		when().get(String.format("http://localhost:%s/find/" + validPostCode, port))
			.then()
			.statusCode(is(200))
			.body(is(expectedResponse.toJsonString()));
	}
}