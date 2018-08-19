package com.vasudev.cardservice.ccprocms.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;
import com.vasudev.cardservice.ccprocms.dto.CreditCardDTO;
import com.vasudev.cardservice.ccprocms.exception.BusinessException;
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

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * GET /getall -> get list of credit cards by page
	 * 
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
		log.debug("Request to get list of the credit card info");
		List<CreditCard> creditCards = creditCardService.findAll();
		return new ResponseEntity<>(
				creditCards.stream().map(CreditCard -> convertToDTO(CreditCard)).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	/**
	 * POST /add -> Add a new credit card
	 *
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<CreditCardDTO> addCreditCard(@RequestBody CreditCardDTO ccDTO) throws BusinessException {
		log.debug("Request to add a new credit card");
		creditCardService.save(convertDTOToEntity(ccDTO));
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	private CreditCardDTO convertToDTO(CreditCard cc) {
		CreditCardDTO ccDTO = modelMapper.map(cc, CreditCardDTO.class);
		return ccDTO;
	}

	private CreditCard convertDTOToEntity(CreditCardDTO ccDTO) throws ParseException {
		CreditCard cc = modelMapper.map(ccDTO, CreditCard.class);
		return cc;
	}

}
