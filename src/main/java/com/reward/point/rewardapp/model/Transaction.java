/* Pranay Sreekonda 
 * Mapping the CSV object 
 */

package com.reward.point.rewardapp.model;

public class Transaction {
	private String name;

	private double transactionAmnt;
	private String date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTransactionAmnt() {
		return transactionAmnt;
	}

	public void setTransactionAmnt(double transactionAmnt) {
		this.transactionAmnt = transactionAmnt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "RewardPoints [name=" + name + ", transactionAmnt=" + transactionAmnt + ", date=" + date + "]";
	}

}
