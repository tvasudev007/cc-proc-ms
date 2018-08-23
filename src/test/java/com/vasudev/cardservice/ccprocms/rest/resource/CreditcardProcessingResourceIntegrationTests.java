package com.vasudev.cardservice.ccprocms.rest.resource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vasudev.cardservice.ccprocms.dto.CreditCardDTO;
import com.vasudev.cardservice.ccprocms.exception.ExceptionResponse;

/**
 * Integration test class, activated in statging profile
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("staging")
public class CreditcardProcessingResourceIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldReturnCreditCardDTOListWhenGetCreditCards() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v1/api/creditCards", CreditCardDTO[].class)).isNotNull();
	}

	@Test
	public void shouldReturnCreatedWhenPostCreditCards() throws Exception {
		CreditCardDTO cc = new CreditCardDTO();
		cc.setName("GOOD CARD");
		cc.setBalance(0.0);
		cc.setLimit(10000L);
		cc.setCardNumber(1141111111111111L);

		HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(cc);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/v1/api/creditCards", HttpMethod.POST, entity, String.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldReturnBadReqWhenPostCreditCards() throws Exception {
		CreditCardDTO cc = new CreditCardDTO();
		cc.setName("BAD CARD");
		cc.setBalance(0.0);
		cc.setLimit(10000L);
		cc.setCardNumber(1141534511111111111L);

		HttpEntity<CreditCardDTO> entity = new HttpEntity<CreditCardDTO>(cc);

		ResponseEntity<ExceptionResponse> response = restTemplate.exchange("http://localhost:" + port + "/v1/api/creditCards", HttpMethod.POST, entity,
				ExceptionResponse.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void shouldReturnBadReqCreditCards() throws Exception {
		// TODO: another conditions like card with 00000000...s
		// TODO: balance set to null or non zero
		// TODO: creating duplicate card
		// TODO: name containing ~`\'' chars
	}

}
