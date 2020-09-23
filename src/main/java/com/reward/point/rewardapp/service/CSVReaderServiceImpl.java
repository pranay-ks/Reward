/* Pranay Sreekonda 
 * This class is reading the CSV file
 * of the customer transaction details
 * and calculate the earned reward point
 */

package com.reward.point.rewardapp.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.reward.point.rewardapp.model.CustomerRewards;
import com.reward.point.rewardapp.model.EarnedRewards;
import com.reward.point.rewardapp.model.MonthWiseSorting;
import com.reward.point.rewardapp.model.Transaction;

@Service("csvReaderService")
@Transactional(readOnly = true)
public class CSVReaderServiceImpl implements CSVReaderService {
	@Autowired
	private EarnedRewards earnedRewards;
	private Logger log = LoggerFactory.getLogger(CSVReaderServiceImpl.class);

	@SuppressWarnings("deprecation")
	@Override
	public EarnedRewards readCSV() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream file = loader.getResourceAsStream("rewards.csv");
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			MappingIterator<Transaction> readValues = mapper.reader(Transaction.class).with(bootstrapSchema)
					.readValues(file);

			List<Transaction> rewardPoints = readValues.readAll();
			EarnedRewards rewards = calculateRewards(rewardPoints);
			System.out.println(rewardPoints);
			Collections.sort(rewards.getCustomerRewards(), new MonthWiseSorting());

			return rewards;
		} catch (Exception e) {
			log.error("Error occurred while loading object list from file ", e);
		}
		return null;
	}

	private EarnedRewards calculateRewards(List<Transaction> transaction) {
		List<CustomerRewards> rewardsList = new ArrayList<>();
		double totalRewards = 0d;
		if (transaction != null && transaction.size() > 0) {

			for (Transaction tr : transaction) {
				CustomerRewards customerRewards = new CustomerRewards();
				double points = 0d;
				if (tr.getTransactionAmnt() >= 50 && tr.getTransactionAmnt() < 100) {
					points = tr.getTransactionAmnt() - 50;
				} else if (tr.getTransactionAmnt() > 100) {
					points = (2 * (tr.getTransactionAmnt() - 100) + 50);
				}
				customerRewards.setName(tr.getName());
				customerRewards.setRewardPoint(points);
				totalRewards += points;
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(tr.getDate());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					customerRewards.setMonth(String.valueOf(calendar.get(Calendar.MONTH)));
					rewardsList.add(customerRewards);
				} catch (ParseException e) {
					log.error("Error:{}", e);
				}
			}
		}
		this.earnedRewards.setCustomerRewards(rewardsList);
		this.earnedRewards.setTotalRewards(totalRewards);
		return this.earnedRewards;
	}
}
