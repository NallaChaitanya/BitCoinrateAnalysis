package com.coindesk.bitcoinrate.model;

import org.springframework.stereotype.Component;

/**
 * @author chaitanya
 *
 *carry Result for Junit Test Cases
 */
@Component
public class FinalResult {

	private String currentRate;
	private Double minmumRate;
	
	private Double maxRate;

	public String getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(String currentRate) {
		this.currentRate = currentRate;
	}

	public Double getMinmumRate() {
		return minmumRate;
	}

	public void setMinmumRate(Double minmumRate) {
		this.minmumRate = minmumRate;
	}

	public Double getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}
	
	
	
	
}
