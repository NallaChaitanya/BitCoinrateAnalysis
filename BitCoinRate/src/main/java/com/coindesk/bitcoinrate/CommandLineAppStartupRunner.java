package com.coindesk.bitcoinrate;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.coindesk.bitcoinrate.client.BitRateClient;

/**
 * @author chaitanya Execution starts from Here..i/p currency code
 */
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	@Autowired
	private BitRateClient restClient;
	private Scanner sc;

	@Override
	public void run(String... args) throws Exception {
		sc = new Scanner(System.in);
		System.out.println("*****GET BIT RATE INFO********");
		System.out.println();
		System.out.println("Please enter the currency code :");
		String currencyCode = sc.nextLine();
		System.out.println();
		System.out.println("......getting info.....");
		if (currencyCode != null && !currencyCode.isEmpty()) {
			restClient.getBitCoinRateInfo(currencyCode.toUpperCase());
		} else {

			System.out.println("Kindly please re-run and enter the currency code ..Thank u");
			System.exit(0);
		}

	}
}