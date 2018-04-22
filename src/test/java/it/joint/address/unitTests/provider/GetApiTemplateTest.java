package it.joint.address.unitTests.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import it.joint.address.provider.AddressResponse;
import it.joint.address.provider.impl.GetAddressTemplate;

import static org.mockito.BDDMockito.then;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
public class GetApiTemplateTest {

    private GetAddressTemplate getAddressTemplate;
    private RestTemplate restTemplate;

    private AddressResponse expectedResponse;

    private String validPostCode = "XX200X";

    @Before
    public void setUp() throws Exception {

	getAddressTemplate = new GetAddressTemplate("http://localhost", "");
	getAddressTemplate = spy(getAddressTemplate);
	restTemplate = mock(RestTemplate.class);

	expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078)
		.withLongitude(-0.1320359706878662).build();
    }

    @Test
    public void givenValidPostCode_whenFindInGetAddress_thenReturnAddressResponse() throws Exception {

	given(getAddressTemplate.getRestTemplate()).willReturn(restTemplate);

	given(restTemplate.getForObject("/find/" + validPostCode, AddressResponse.class)).willReturn(expectedResponse);

	AddressResponse addressResponse = getAddressTemplate.find(validPostCode);

	then(getAddressTemplate).should().getRestTemplate();

	then(restTemplate).should().getForObject("/find/" + validPostCode, AddressResponse.class);

	assertThat(addressResponse, equalTo(expectedResponse));
    }
}