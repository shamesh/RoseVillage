package com.rosevillage.flowers;

/**
 * @author Shamesh Joshi 
 * Bundle stores each flower bundle information.
 */
public class Bundle {
	private final String name;
	private final Double value;
	private final Integer size;

	public Bundle(String name, Double i, Integer size) {
		this.name = name;
		this.value = i;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public Integer getSize() {
		return size;
	}

	public Double getValue() {
		return value;
	}

	public Double getValueToSizeRatio() {
		return (double) value / (double) size;
	}
}
