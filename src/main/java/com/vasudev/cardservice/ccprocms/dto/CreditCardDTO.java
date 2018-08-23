package com.vasudev.cardservice.ccprocms.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO for credit card
 * 
 * @author vasudev007
 *
 */
public class CreditCardDTO {

	private String name;

	private Long cardNumber;

	private Double balance;

	private Long limit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		// @formatter:off
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("name", this.name)
				.append("cardNumber", this.cardNumber).append("balance", this.balance).append("limit", this.limit)
				.toString();
		// @formatter:on
	}

}
