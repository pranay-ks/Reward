/* Pranay Sreekonda 
 * JSON Response Mapper
 * to map the response of customer rewards and total rewards 
 */

package com.reward.point.rewardapp.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EarnedRewards {
	private List<CustomerRewards> customerRewards;
	private double totalRewards;

	public List<CustomerRewards> getCustomerRewards() {
		return customerRewards;
	}

	public void setCustomerRewards(List<CustomerRewards> customerRewards) {
		this.customerRewards = customerRewards;
	}

	public double getTotalRewards() {
		return totalRewards;
	}

	public void setTotalRewards(double totalRewards) {
		this.totalRewards = totalRewards;
	}

	@Override
	public String toString() {
		return "EarnedRewards [customerRewards=" + customerRewards + ", totalRewards=" + totalRewards + "]";
	}

}
