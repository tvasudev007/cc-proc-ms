package com.vasudev.cardservice.ccprocms.utils;

public class CreditCardValidatorUtil {

	public static Boolean ValidateCCUsingLuhn(Long creditCardNumber) {

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
		return (oddSum + evenSum) % 10 == 0;
	}
}
