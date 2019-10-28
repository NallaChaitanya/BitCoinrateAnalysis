package com.coindesk.bitcoinrate.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author chaitanya
 * Utility class for BaseRATE
 */
public class BitRateUtils {

	
	/**
	 * Gives start and end date
	 * @param date
	 * @return String date
	 */
	public static String getStartandEndDate(Date date) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String getDate = simpleDateFormat.format(date);
		return getDate;

	}
	
	/**
	 * Gets 30 days prior date
	 * @return Date
	 */
	public static Date getLastMonthDate() {
		
		 Calendar cal = Calendar.getInstance();
		 cal.add(Calendar.DATE, -30);
		  return  cal.getTime();
	}
	
	
	/**
	 * Gives key of particular value
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param value
	 * @return
	 */
	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
