/* Pranay Sreekonda 
 * This class sorts the customer reward object by month wise
 */

package com.reward.point.rewardapp.model;

import java.util.Comparator;

public class MonthWiseSorting implements Comparator<CustomerRewards> {

	@Override
	public int compare(CustomerRewards cr1, CustomerRewards cr2) {
		if (Integer.valueOf(cr1.getMonth()) > Integer.valueOf(cr2.getMonth())) {
			return 1;
		} else if (Integer.valueOf(cr1.getMonth()) < Integer.valueOf(cr2.getMonth())) {
			return -1;
		}
		return 0;
	}

}
