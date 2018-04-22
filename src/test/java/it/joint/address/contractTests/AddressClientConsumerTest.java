package it.joint.address.contractTests;

import au.com.dius.pact.consumer.Pact;

import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import it.joint.address.client.AddressClient;
import it.joint.address.provider.AddressResponse;
import it.joint.address.util.TestUtil;

import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressClientConsumerTest {

    @Autowired
    private AddressClient addressClient;

    private String validPostCode = "XX200X";

    private AddressResponse expectedResponse;

    @Before
    public void setUp() {

	expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078)
		.withLongitude(-0.1320359706878662)
		.withAddresses(
			Arrays.asList(new String[] { "10 Watkin Terrace, , , , , Northampton, Northamptonshire" }))
		.build();
    }

    @Rule
    public PactProviderRuleMk2 addressApiService = new PactProviderRuleMk2("address_api_service", "localhost", 9000,
	    this);

    @Pact(consumer = "address")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws Exception {

	return builder.given("a valid postcode").uponReceiving("a find request by a valid postcode")
		.path("/find/XX200X").query("api-key=someApiKey").method("GET").willRespondWith().status(200)
		.body(TestUtil.fileAsJson("classpath:addressResponse.json"), ContentType.APPLICATION_JSON).toPact();
    }

    @Test
    @PactVerification("address_api_service")
    public void givenValidPostCode_whenFindAddresses_thenReturnAddressResponse() throws Exception {

	AddressResponse addressResponse = addressClient.findAddresses(validPostCode);

	assertThat(addressResponse, is(expectedResponse));
    }
}
