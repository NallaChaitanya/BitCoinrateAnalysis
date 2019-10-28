package com.coindesk.bitcoinrate.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author chaitanya
 *Bite rate response Model
 */
@JsonIgnoreProperties
public class BitRateResponse {
	private Object time;
	
	private String disclaimer;
	private String chartName;

	private Map<String, RateByCurrency> bpi;

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

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Map<String, RateByCurrency> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, RateByCurrency> bpi) {
		this.bpi = bpi;
	}

		
}
