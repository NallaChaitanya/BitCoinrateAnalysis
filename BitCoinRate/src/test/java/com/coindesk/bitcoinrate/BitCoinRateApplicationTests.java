package com.coindesk.bitcoinrate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.coindesk.bitcoinrate.client.BitRateClient;
import com.coindesk.bitcoinrate.model.FinalResult;

@RunWith(SpringRunner.class)
class BitCoinRateApplicationTests {

	
	/**
	 * pass currency code and pass expect test results
	 * this method will cover all test cases.
	 * please pass the values
	 * and run test cases
	 */
	@Test
	void testBitrateInfo() {
		
		BitRateClient bc=new BitRateClient();
		//pass here currency code;
		FinalResult freFinalResult=bc.getBitCoinRateInfo("");
		assertEquals(new Double("pass expected val vlaue"), freFinalResult.getMaxRate());
		assertEquals(new Double("pass expected val vlaue"), freFinalResult.getMinmumRate());
		assertEquals("pass expected current rate",freFinalResult.getCurrentRate());
	}
	
	

}
