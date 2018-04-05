package it.joint.address.componentTests;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import it.joint.address.BaseTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AddressComponentTest extends BaseTest {

	@LocalServerPort
	private int randomPort;
    
	@Autowired
	private UriComponentsBuilder localHostUrl;
	
	@Rule
	public WireMockRule addressApiService = new WireMockRule(9000);

	@Test
	public void givenValidPostCode_whenGetFindUrl_thenStatusCodeIs200AndBodyIsExpectedResponse() throws Exception {

		String validPostCode = "XX200X";

		String findPath = UriComponentsBuilder
						  .fromPath("/find/{postcode}")
		         	      .buildAndExpand(validPostCode)
		         		  .toString();
		
		String findUrl = localHostUrl
				         .port(randomPort)
				         .path(findPath)
				         .buildAndExpand(validPostCode)
				         .toString();
		
		addressApiService.stubFor(get(urlPathEqualTo(findPath))
						 .willReturn(aResponse()
								 	 .withBody(expectedResponseJson())
								 	 .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
								 	 .withStatus(200)));

		when()
		.get(findUrl)
		.then()
		.statusCode(is(200))
		.body(is(expectedResponse.toJsonString()));
	}
}