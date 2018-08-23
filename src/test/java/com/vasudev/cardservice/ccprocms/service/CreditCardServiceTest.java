package com.vasudev.cardservice.ccprocms.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;

/**
 * Test class for CreditCardService
 *
 */
@RunWith(SpringRunner.class)
public class CreditCardServiceTest {

	@Autowired
	private CreditCardService toTest;

	@Before
	public void setUp() {
		CreditCard cc = new CreditCard();

		cc.setBalance(312312.526732);
		cc.setCardNumber(1234590123331L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);

		CreditCard cc1 = new CreditCard();

		cc1.setBalance(312312.526732);
		cc1.setCardNumber(21234590123331L);
		cc1.setName("TEST CARD 1");
		cc1.setLimit(15000L);

		CreditCard cc2 = new CreditCard();

		cc2.setBalance(312312.526732);
		cc2.setCardNumber(254690123331L);
		cc2.setName("TEST CARD 2");
		cc2.setLimit(15000L);

		List<CreditCard> ccList = new ArrayList<>();

		ccList.add(cc);
		ccList.add(cc1);
		ccList.add(cc2);

		Mockito.when(toTest.findAll()).thenReturn(ccList);
	}

	@Test
	public void whenFindAll_thenReturnAllCreditCards() {

		List<CreditCard> actual = toTest.findAll();

		assertThat(actual).isNotNull();

	}

	@Test
	public void whenSaveGood_thenThrowNoException() {

		// TODO: Check for all the conditions

	}

	@Test
	public void whenSaveBadNumber_thenThrowException() {

		// TODO: Check for all the conditions

	}

}
