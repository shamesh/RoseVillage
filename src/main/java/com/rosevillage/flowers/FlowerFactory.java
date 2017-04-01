package com.rosevillage.flowers;

public class FlowerFactory {

	public Flower getFlower(String flowerCode) {
		Flower flower = null;
		if ("L09".equalsIgnoreCase(flowerCode))
			flower = new Lilies();
		else if ("R12".equalsIgnoreCase(flowerCode))
			flower = new Rose();
		else if ("T58".equalsIgnoreCase(flowerCode))
			flower = new Tulips();
		return flower;
	}

}
