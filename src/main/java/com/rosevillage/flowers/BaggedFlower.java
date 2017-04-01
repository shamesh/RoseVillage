package com.rosevillage.flowers;

/**
 * @author Shamesh Joshi
 * This class will store the bundles of flower used. 
 */
public class BaggedFlower {
	private final Bundle bundle;
	private final Integer counter;

	public BaggedFlower(Bundle bundle, Integer counter) {
		this.bundle = bundle;
		this.counter = counter;
	}

	public Bundle getItem() {
		return bundle;
	}

	public Integer getCounter() {
		return counter;
	}

	public Double getTotalValue() {
		return bundle.getValue() * counter;
	}

	public Integer getTotalSize() {
		return bundle.getSize() * counter;
	}

}
