/* Pranay Sreekonda 
 * JSON Response Mapper
 * to map the response of the rewards object 
 */

package com.reward.point.rewardapp.model;

public class CustomerRewards {
	private String name;
	private String month;
	private double rewardPoint;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(double rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	@Override
	public String toString() {
		return "CustomerRewards [name=" + name + ", month=" + month + ", rewardPoint=" + rewardPoint + "]";
	}

}
