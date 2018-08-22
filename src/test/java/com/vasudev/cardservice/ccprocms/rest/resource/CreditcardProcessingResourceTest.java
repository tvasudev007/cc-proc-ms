package com.vasudev.cardservice.ccprocms.rest.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vasudev.cardservice.ccprocms.service.CreditCardService;

@RunWith(SpringRunner.class)
@WebMvcTest(CreditcardProcessingResource.class)
public class CreditcardProcessingResourceTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CreditCardService service;
    
    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        
    }

}
