package it.joint.address.acceptanceTests;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.joint.address.acceptanceTests.config.AcceptanceTestsConfiguration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import java.net.URI;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AcceptanceTestsConfiguration.class })
public class AddressE2ETest {

	@Autowired
	private URI baseUri;
	
	String validPostCode = "XX200X";
	
	@Test
	public void givenValidPostCode_whenGetFind_thenStatusCodeIs200() {

		when().get(baseUri.toString() + "/find/" + validPostCode)
			.then()
			.statusCode(equalTo(200));
	}
}