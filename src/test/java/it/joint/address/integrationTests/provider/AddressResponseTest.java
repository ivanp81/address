package it.joint.address.integrationTests.provider;

import it.joint.address.provider.AddressResponse;
import it.joint.address.util.TestUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@JsonTest
public class AddressResponseTest {

    @Autowired
	private JacksonTester<AddressResponse> json;

    private AddressResponse expectedResponse;
    
	@Before
    public void setUp() {
		
		expectedResponse = new AddressResponse.Builder()
								.withLatitude(51.39020538330078)
								.withLongitude(-0.1320359706878662)
								.withAddresses(Arrays.asList(new String[] { "10 Watkin Terrace, , , , , Northampton, Northamptonshire"}))
								.build();
    }
	
	@Test
	public void testSerializedAddressResponse() throws Exception {
		assertThat(serializedAddressResponse()).isEqualToJson(TestUtil.fileAsJson("classpath:addressResponse.json"));
	}

	@Test
	public void testDeserializedAddressResponse() throws Exception {
		assertThat(deserializedAddressResponse()).isEqualTo(expectedResponse);
	}
	
	private JsonContent<AddressResponse> serializedAddressResponse() throws IOException {
		return this.json.write(expectedResponse);
	}
	
	private ObjectContent<AddressResponse> deserializedAddressResponse() throws IOException {
		return this.json.parse(TestUtil.fileAsJson("classpath:addressResponse.json"));
	}
}