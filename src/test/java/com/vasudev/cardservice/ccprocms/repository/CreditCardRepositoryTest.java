package com.vasudev.cardservice.ccprocms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.vasudev.cardservice.ccprocms.domain.CreditCard;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardRepositoryTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CreditCardRepository ccRepository;

	@Test
	public void whenFindAll_thenReturnCreditCards() {
		// given
		CreditCard cc = new CreditCard();
		cc.setBalance(312312.526732);
		cc.setCardNumber(1234567890123331L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);

		entityManager.persist(cc);
		entityManager.flush();

		// when
		List<CreditCard> found = ccRepository.findAll();

		// then
		assertThat(found.size()).isNotNull();

		assertThat(found.contains(cc)).isTrue();
	}

	@Test
	public void whenFindOne_thenReturnCreditCard() {
		// given
		CreditCard cc = new CreditCard();
		cc.setBalance(312312.52673232134124125412412412412412412412412412412412412412411111111);
		cc.setCardNumber(1234567890123331L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);

		entityManager.persist(cc);
		entityManager.flush();

		// when
		CreditCard foundOne = ccRepository.findOneByCardNumber(cc.getCardNumber());

		// then
		assertThat(foundOne).isNotNull();

		assertThat(foundOne.getBalance().equals(cc.getBalance())).isTrue();
	}

	@Test
	public void whenFindAll_thenReturnCreditCardsNotContains() {
		// when
		List<CreditCard> found = ccRepository.findAll();

		CreditCard cc = new CreditCard();
		cc.setBalance(0.0);
		cc.setCardNumber(1234567890123L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);

		// given : null

		// then
		assertThat(found.contains(cc)).isFalse();
	}

	@Test
	public void whenCreateOne_thenReturnCreditCard() {

		// when
		CreditCard cc = new CreditCard();
		cc.setBalance(0.0);
		cc.setCardNumber(1234567890123L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);
		ccRepository.save(cc);

		// given
		CreditCard foundOne = ccRepository.findOneByCardNumber(cc.getCardNumber());

		// then
		assertThat(foundOne.equals(cc)).isTrue();
	}

	@Test(expected = org.hibernate.exception.ConstraintViolationException.class) 
	public void whenCreateDuplicate_thenReturnError()  {
		
		// when
		CreditCard cc = new CreditCard();
		cc.setBalance(0.0);
		cc.setCardNumber(1234567890123L);
		cc.setName("TEST CARD");
		cc.setLimit(15000L);
		entityManager.persist(cc);
		entityManager.flush();
		
		CreditCard cc2 = new CreditCard();
		cc2.setBalance(0.0);
		cc2.setCardNumber(1234567890123L);
		cc2.setName("TEST CARD");
		cc2.setLimit(15000L);
		entityManager.persist(cc2);
		entityManager.flush();
		



	}


}
