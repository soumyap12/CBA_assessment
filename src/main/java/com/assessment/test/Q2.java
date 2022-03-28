package com.assessment.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Q2 extends Q2page{

	@Test(priority=1)
	public void verifyLoanAmountFor1Year() {
		SoftAssert as= new SoftAssert();
		String[] s= getRepayAndInterestTotal("45000", "30","Interest only 1 year","4.14% p.a. 3 Year Fixed Rate Home Loan");
		as.assertEquals(s[0], "$76,406");
		as.assertEquals(s[1], "$31,406");
		as.assertAll();
	}

}
