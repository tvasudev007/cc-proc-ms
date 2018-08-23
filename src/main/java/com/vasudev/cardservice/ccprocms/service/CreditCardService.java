package com.vasudev.cardservice.ccprocms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;
import com.vasudev.cardservice.ccprocms.exception.BusinessException;
import com.vasudev.cardservice.ccprocms.repository.CreditCardRepository;
import com.vasudev.cardservice.ccprocms.utils.CreditCardValidatorUtil;

/**
 * Service class for managing Asset.
 */
@Service
@Transactional
public class CreditCardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String REGEX_CARD_NUMBER = "^[0-9]{10,19}$";
	private static final String REGEX_NAME = "^[a-zA-Z0-9 _#.-]*$";
	private static final String REGEX_LIMIT = "^-?\\d{1,19}$";

	@Autowired
	private CreditCardRepository creditCardRepository;

	/**
	 * To persist a new credit card
	 * 
	 * @param cc
	 * @throws BusinessException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(CreditCard cc) throws BusinessException {

		log.trace("Validating credit card");

		Long ccNumber = cc.getCardNumber();

		if (unitaryCheck(ccNumber) != null) {
			throw new BusinessException(BusinessException.DUPLICATE_CC_NUM);
		}

		if (!CreditCardValidatorUtil.ValidateCCUsingLuhn(ccNumber) || !Long.toString(ccNumber).matches(REGEX_CARD_NUMBER)) {
			throw new BusinessException(BusinessException.INVALID_CC_NUM);
		}

		if (StringUtils.isEmpty(cc.getName()) || !cc.getName().matches(REGEX_NAME)) {
			throw new BusinessException(BusinessException.INVALID_CC_NAME);
		}

		if (!Long.toString(cc.getLimit()).matches(REGEX_LIMIT)) {
			throw new BusinessException(BusinessException.INVALID_CC_LIMIT);
		}

		if (cc.getBalance() != 0) {
			throw new BusinessException(BusinessException.INVALID_CC_BALANCE);
		}

		log.debug("Saving new credit card : {}", cc);
		creditCardRepository.save(cc);
	}

	/**
	 * Get credit cards by page
	 * 
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<CreditCard> findAllByPage(Pageable pageable) {
		log.trace("Finding all credit cards by page");
		return creditCardRepository.findAll(pageable);
	}

	/**
	 * Get all credit cards stored
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CreditCard> findAll() {
		log.trace("Finding all credit cards");
		return creditCardRepository.findAll();
	}

	/**
	 * To check for exisiting card number
	 * 
	 * @param ccNumber
	 * @return
	 */
	private CreditCard unitaryCheck(Long ccNumber) {
		log.trace("card number unitary check");
		return creditCardRepository.findOneByCardNumber(ccNumber);
	}

}
