package com.vasudev.cardservice.ccprocms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;

/**
 * Repository class to manage credit card entity
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	Page<CreditCard> findAll(Pageable pageable);

	List<CreditCard> findAll();

	CreditCard findOneByCardNumber(@Param("cardNumber") Long cardNumber);
}
