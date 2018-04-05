package it.joint.address.integrationTests.client;

import it.joint.address.BaseTest;
import it.joint.address.client.provider.AddressResponse;

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

@RunWith(SpringRunner.class)
@JsonTest
public class AddressResponseTest extends BaseTest {

    @Autowired
	private JacksonTester<AddressResponse> json;

	@Test
	public void testSerializedAddressResponse() throws Exception {
		assertThat(serializedAddressResponse())
				  .isEqualToJson(expectedResponseJson());
	}

	@Test
	public void testDeserializedAddressResponse() throws Exception {
		assertThat(deserializedAddressResponse())
				  .isEqualTo(expectedResponse);
	}
	
	private JsonContent<AddressResponse> serializedAddressResponse() throws IOException {
		return this.json.write(expectedResponse);
	}
	
	private ObjectContent<AddressResponse> deserializedAddressResponse() throws IOException {
		return this.json.parse(expectedResponseJson());
	}
}