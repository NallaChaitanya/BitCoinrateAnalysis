package com.coindesk.bitcoinrate.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author chaitanya
 *
 *Model for history
 */
@JsonIgnoreProperties
public class BitRateHistoryResponse {

	private Map<String, Double> bpi;
	private String disclaimer;
	
	private Object time;
	

	public Object getTime() {
		return time;
	}

	public void setTime(Object time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, Double> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, Double> bpi) {
		this.bpi = bpi;
	}
	
	
}
