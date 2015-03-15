/**
 * 
 */
package com.profit;

import java.text.DecimalFormat;

/**
 * Calculate the Max profit given array of rates on each day.
 * @author D.V.Santhosh
 *
 */
public class ProfitMaximizer {

	/**
	 * Used for test purpose.
	 * Prints the max profit for an array.
	 * @param args Not required
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double[] rates = {100.89,80.15,2.36,10.12,8.01,1006.25,1.01,100.84,5.07,1100.9};
		
		// for formatting to two decimals
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println(" The Maximum profit is $"+ df.format(maxProfit(rates)));

	}
	
	/**
	 * Calculates the max profit given the array containing rates
	 * @param rates takes array of rates as input
	 * @return maxProfit returns the max profit from a buy and sale transaction.
	 */
	public static double maxProfit(double[] rates){
		double maxProfit =rates[1]-rates[0];
		double cheapest = rates[0];
		for (int i = 0; i < rates.length; i++) {
			if(rates[i]-cheapest > maxProfit)
				maxProfit = rates[i]-cheapest;
			
			if (rates[i] < cheapest)
				cheapest = rates[i];
		}		
		return maxProfit;
	}

}
