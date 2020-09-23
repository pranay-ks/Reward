package com.reward.point.rewardapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reward.point.rewardapp.model.EarnedRewards;
import com.reward.point.rewardapp.service.CSVReaderService;

@RestController
public class RewardPointsController {
	@Autowired
	private CSVReaderService csvReaderService;
	
	@GetMapping("/rewards")
	public EarnedRewards calculateRewardPoints(){
		return this.csvReaderService.readCSV();
	}
}
