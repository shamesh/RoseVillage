package com.rosevillage.calculation;

import java.text.DecimalFormat;
import java.util.Collection;

import com.rosevillage.flowers.BaggedFlower;

public final class Solution {
	private final Collection<BaggedFlower> baggedFlowers;

	Solution(Collection<BaggedFlower> baggedFlowers) {
		this.baggedFlowers = baggedFlowers;
	}

	public Collection<BaggedFlower> getBaggedFlowers() {
		return baggedFlowers;
	}

	public Double getTotalValue() {
		Double total = 0.0;
		for (BaggedFlower baggedFlower : baggedFlowers) {
			total += baggedFlower.getTotalValue();
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(total));
	}

	public Integer getTotalSize() {
		Integer total = 0;
		for (BaggedFlower baggedFlower : baggedFlowers) {
			total += baggedFlower.getTotalSize();
		}
		return total;
	}

	public Integer getTotalItems() {
		Integer total = 0;
		for (BaggedFlower baggedFlower : baggedFlowers) {
			total += baggedFlower.getCounter();
		}
		return total;
	}

}
