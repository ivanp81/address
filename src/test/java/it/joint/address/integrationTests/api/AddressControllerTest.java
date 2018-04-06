package it.joint.address.integrationTests.api;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import it.joint.address.api.AddressController;
import it.joint.address.client.AddressClient;
import it.joint.address.provider.AddressResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
@ActiveProfiles("test")
public class AddressControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AddressClient addressClient;
    
    AddressResponse expectedResponse;
    
    @Before
    public void setUp() throws Exception {
    	initMocks(this);
        expectedResponse = new AddressResponse.Builder().withLatitude(51.39020538330078).withLongitude(-0.1320359706878662).build();
    }
    
    @Test
    public void givenValidPostCode_whenGetFind_thenReturnAddressResponse() throws Exception {

        String validPostCode = "XX200X";

        given(addressClient.findAddresses(validPostCode))
        	.willReturn(expectedResponse);

        mvc.perform(get("/find/" + validPostCode)
        	.contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResponse.toJsonString()));
        
        then(addressClient)
			.should()
			.findAddresses(validPostCode);
    }    
}