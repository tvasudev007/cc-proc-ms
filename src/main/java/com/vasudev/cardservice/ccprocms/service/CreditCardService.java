package com.vasudev.cardservice.ccprocms.service;

import java.util.List;

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
import com.vasudev.cardservice.ccprocms.utils.CreditCardNumberValidatorUtil;

/**
 * Service class for managing Asset.
 */
@Service
@Transactional
public class CreditCardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(CreditCard cc) throws BusinessException {

		Long ccNumber = cc.getCardNumber();

		if (unitaryCheck(ccNumber) != null) {
			throw new BusinessException(BusinessException.DUPLICATE_CC_NUM);
		}

		int length = (int) (Math.log10(ccNumber) + 1);

		if (!CreditCardNumberValidatorUtil.ValidateCCUsingLuhn(ccNumber) || (length <= 0 || length > 20)) {
			throw new BusinessException(BusinessException.INVALID_CC_NUM);
		}
		log.debug("Saving new credit card : {}", cc);
		creditCardRepository.save(cc);
	}

	@Transactional(readOnly = true)
	public Page<CreditCard> findAllByPage(Pageable pageable) {
		log.debug("Finding all credit cards by page");
		return creditCardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<CreditCard> findAll() {
		log.debug("Finding all credit cards");
		return creditCardRepository.findAll();
	}

	private CreditCard unitaryCheck(Long ccNumber) {
		return creditCardRepository.findOneByCardNumber(ccNumber);
	}

}
