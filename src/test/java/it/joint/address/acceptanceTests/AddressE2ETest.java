package it.joint.address.acceptanceTests;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import it.joint.address.config.UrlConfiguration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UrlConfiguration.class })
public class AddressE2ETest {

	@Autowired
	private UriComponentsBuilder addressUrl;
	
	@Test
	public void givenValidPostCode_whenGetFindUrl_thenStatusCodeIs200() {

		String validPostCode = "XX200X";
		
		String findUrl = addressUrl.path("/find/{postCode}")
								   .buildAndExpand(validPostCode)
								   .toString();
		
		when()
		.get(findUrl)
		.then()
		.statusCode(is(200));
	}
}