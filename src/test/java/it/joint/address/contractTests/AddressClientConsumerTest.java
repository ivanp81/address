package it.joint.address.contractTests;

import au.com.dius.pact.consumer.Pact;

import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import it.joint.address.BaseTest;
import it.joint.address.client.AddressClient;
import it.joint.address.client.provider.AddressResponse;

import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressClientConsumerTest extends BaseTest {

    @Autowired
    private AddressClient addressClient;
    
    @Rule
    public PactProviderRuleMk2 addressApiService = new PactProviderRuleMk2("address_api_service", "localhost", 9000, this);

    @Pact(consumer="address")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws Exception {
        
    	return builder
               .given("a valid postcode")
               .uponReceiving("a find request by a valid postcode")
               		.path("/find/XX200X")
               		.query("api-key=someApiKey")
               		.method("GET")
               .willRespondWith()
                    .status(200)
                    .body(expectedResponseJson(), ContentType.APPLICATION_JSON)
               .toPact();
    }

    @Test
    @PactVerification("address_api_service")
    public void givenValidPostCode_whenFindAddresses_thenActualResponseIsExpectedResponse() throws Exception {
    	
    	String validPostCode = "XX200X";
    	AddressResponse actualResponse = addressClient.findAddresses(validPostCode);
      
        assertThat(actualResponse, is(expectedResponse));
    }
}
