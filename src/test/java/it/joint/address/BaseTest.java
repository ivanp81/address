package it.joint.address;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;

import it.joint.address.client.provider.AddressResponse;
import it.joint.address.helper.JsonFile;

public abstract class BaseTest {
	
	protected AddressResponse expectedResponse;
	
	@Before
	public void setUpExpectedResponse() {
		
		AddressResponse.Builder buildExpectedResponse = new AddressResponse.Builder();
		
		expectedResponse = buildExpectedResponse
						   .withLatitude("51.390205383300781")
			               .withLongitude("-0.13203597068786621")
			               .withAddresses(Arrays.asList(new String[] { "10 Watkin Terrace, , , , , Northampton, Northamptonshire"}))
			               .build();
	}
	
	protected String expectedResponseJson() throws IOException {
		return new JsonFile
				  .Builder()
				  .fromPath("classpath:addressApiResponse.json")
				  .build()
				  .asString();
	}
}
