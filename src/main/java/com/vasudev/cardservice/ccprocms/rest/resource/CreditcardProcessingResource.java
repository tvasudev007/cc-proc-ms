package com.vasudev.cardservice.ccprocms.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;
import com.vasudev.cardservice.ccprocms.service.CreditCardService;

import io.micrometer.core.annotation.Timed;

/**
 * @author vasudev007
 *
 */
@RestController
@RequestMapping(value = "/v1/api/creditcard")
public class CreditcardProcessingResource {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CreditCardService creditCardService;

	// TODO add @Secured()
	/**
	 * GET /getall -> get list of credit cards by page
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<?> getAllCreditCards(Pageable pageable) {
		log.debug("Request to get list of the credit card info");
		return new ResponseEntity<>(creditCardService.findAll(), HttpStatus.OK);
	}

	/**
	 * POST /add -> Add a new credit card
	 *
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<?> addCreditCard(@RequestBody CreditCard cc) {

		log.debug("Request to add a new credit card");

		creditCardService.save(cc);

		return new ResponseEntity<>(creditCardService.findAll(), HttpStatus.OK);
	}

}
