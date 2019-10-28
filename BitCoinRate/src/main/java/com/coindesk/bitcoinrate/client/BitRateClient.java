package com.coindesk.bitcoinrate.client;

import java.util.Collections;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.coindesk.bitcoinrate.constants.BitRateConstants;
import com.coindesk.bitcoinrate.model.BitRateHistoryResponse;
import com.coindesk.bitcoinrate.model.BitRateResponse;
import com.coindesk.bitcoinrate.model.FinalResult;
import com.coindesk.bitcoinrate.util.BitRateUtils;

/**
 * @author chaitanya BitRateClient is designed to analyze Bit coin Rates Current
 *         Rate,Highest and lowest Rates last 30 days
 */
@Component
public class BitRateClient {

	/**
	 * @param currencyCode mETHOD THAT GIVE ALL DATA FOR THE REQUIREMENT
	 */
	public FinalResult getBitCoinRateInfo(String currencyCode) {

		// START DATE AND end date
		String startDate = BitRateUtils.getStartandEndDate(new Date());
		String enDate = BitRateUtils.getStartandEndDate(BitRateUtils.getLastMonthDate());
		//to send the values for JUNIT
		FinalResult result=new FinalResult();

		// CURRENT RATE
		BitRateResponse bitRateResponse = (BitRateResponse) this.createClient(BitRateConstants.CURRENT_CURRNCY_URI,
				currencyCode, BitRateConstants.EMPTY, BitRateConstants.EMPTY, BitRateConstants.CURRENT_RATE);

		System.out.println();
		System.out.println("CURRENT BIT COIN RATE FOR CURRENCY CODE :: " + currencyCode + " ::  is  "
				+ bitRateResponse.getBpi().get(currencyCode).getRate() + " -> "
				+ bitRateResponse.getBpi().get(currencyCode).getDescription());

		result.setCurrentRate(bitRateResponse.getBpi().get(currencyCode).getRate());
		// hISTORY
		BitRateHistoryResponse bitRateHistoryResponse = (BitRateHistoryResponse) this.createClient(
				BitRateConstants.HISTORY_URI, currencyCode, enDate, startDate, BitRateConstants.BITCOIN_HISTORY);

		// getting minimum value using min()
		Double minValue = Collections.min(bitRateHistoryResponse.getBpi().values());

		// getting maximum value using max()
		Double maxValue = Collections.max(bitRateHistoryResponse.getBpi().values());
		
		result.setMinmumRate(minValue);
		result.setMaxRate(maxValue);

		System.out.println();
		// printing the minimum value
		System.out.println("Minimum Rate of bitcoin in the last 30 days for the currency code " + currencyCode + " -> "
				+ bitRateResponse.getBpi().get(currencyCode).getDescription() + " is: " + minValue + " on date "
				+ BitRateUtils.getKey(bitRateHistoryResponse.getBpi(), minValue));

		System.out.println();
		// printing the maximum value
		System.out.println("Maximum Rate of bitcoin in the last 30 days for the currency  code " + currencyCode + " -> "
				+ bitRateResponse.getBpi().get(currencyCode).getDescription() + " is: " + maxValue + " on date "
				+ BitRateUtils.getKey(bitRateHistoryResponse.getBpi(), maxValue));
		
		return result;

	}

	/**
	 * This method create and binds connect to rest api and give result object IN A
	 * MOST gENERIC WAY
	 * 
	 * @param basePath
	 * @param currencyCode
	 * @param startDate
	 * @param endDate
	 * @param reqType
	 * @return
	 */
	private Object createClient(String basePath, String currencyCode, String startDate, String endDate,
			String reqType) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = null;
		Object returnGenricObj = null;
		if (reqType.equalsIgnoreCase(BitRateConstants.CURRENT_RATE)) {
			// CURRENT RATE
			webTarget = client.target(basePath).path(currencyCode + ".json");

		} else {
			// HISTORY LAST 30 DAYS
			webTarget = client.target(basePath).queryParam(BitRateConstants.Q_PARAM_CURRENCY, currencyCode)
					.queryParam(BitRateConstants.Q_PARAM_START, startDate)
					.queryParam(BitRateConstants.Q_PARAM_END, endDate);

		}

		// INVOKE CONNECTION
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		// CHECK ERROR CODE
		checkError(response, reqType);
		// MAPP RESPONSE OBJECT
		returnGenricObj = reqType.equalsIgnoreCase(BitRateConstants.CURRENT_RATE)
				? response.readEntity(BitRateResponse.class)
				: response.readEntity(BitRateHistoryResponse.class);

		return returnGenricObj;
	}

	/**
	 * CHECK CODE AND GIVE ERROR OTHER THAN 200
	 * 
	 * @param response
	 * @param reqType
	 */
	private void checkError(Response response, String reqType) {

		if (reqType.equalsIgnoreCase(BitRateConstants.CURRENT_RATE) && response.getStatus() != 200) {

			System.out.println("Currency code Not found, please try with valid code.");
			System.exit(0);

		}
		if (reqType.equalsIgnoreCase(BitRateConstants.BITCOIN_HISTORY) && response.getStatus() != 200) {

			System.out.println("History Api Issues..!");
			System.exit(0);

		}
	}
}
