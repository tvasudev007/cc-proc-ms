package com.vasudev.cardservice.ccprocms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;

/**
 * @author vasudev007
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	Page<CreditCard> findAll(Pageable pageable);
	
	List<CreditCard> findAll();
}
