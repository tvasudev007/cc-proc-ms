package com.vasudev.cardservice.ccprocms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to validate credit card number
 *
 */
public class CreditCardValidatorUtil {

	private static final Logger log = LoggerFactory.getLogger(CreditCardValidatorUtil.class);

	public static Boolean ValidateCCUsingLuhn(Long creditCardNumber) {

		log.trace("Creditcard number: {}", creditCardNumber);

		Boolean isCardValid = false;

		String number = Long.toString(creditCardNumber);
		//
		int oddSum = 0, evenSum = 0;
		String reverseOfNumber = new StringBuffer(number).reverse().toString();

		for (int i = 0; i < reverseOfNumber.length(); i++) {
			int digit = Character.getNumericValue(reverseOfNumber.charAt(i));//

			if (i % 2 == 0) {// sum of digits located in odd places
				oddSum += digit;
			} else {// sum of twice the digits in even places, (if number => 5
					// subtract 9)
				evenSum += 2 * digit;
				if (digit >= 5) {
					evenSum -= 9;
				}
			}
		}

		isCardValid = (oddSum + evenSum != 0) && (oddSum + evenSum) % 10 == 0;
		log.trace("Is card Valid: {}", isCardValid);
		return isCardValid;
	}
}
