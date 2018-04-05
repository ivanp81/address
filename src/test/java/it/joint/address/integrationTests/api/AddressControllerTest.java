package it.joint.address.integrationTests.api;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import it.joint.address.BaseTest;
import it.joint.address.api.AddressController;
import it.joint.address.client.AddressClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
@ActiveProfiles("test")
public class AddressControllerTest extends BaseTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AddressClient addressClient;
    
    @Test
    public void givenValidPostCode_whenGetFindUrl_thenStatusCodeIsOkAndContentIsExpectedResponse() throws Exception {

        String validPostCode = "XX200X";

        String findUrl = UriComponentsBuilder
        			     .fromPath("/find/{postcode}")
				 		 .buildAndExpand(validPostCode)
				 		 .toString();

        given(addressClient.findAddresses(validPostCode))
        .willReturn(expectedResponse);

        mvc.perform(get(findUrl)
        		   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk())
                   .andExpect(content().string(expectedResponse.toJsonString()));
    }    
}