package com.rosevillage.flowers;

import java.util.LinkedList;
import java.util.List;

public class Rose extends Flower {

	public Rose() {
		this.setName("Roses");
		this.setCode("R12");
		this.setAvailableBundle(createBundle());

	}

	private List<Bundle> createBundle() {
		List<Bundle> bundle = new LinkedList<>();
		bundle.add(new Bundle("Rose 10 Bunch", 12.99, 10));
		bundle.add(new Bundle("Rose 5 Bunch", 6.99, 5));
		return bundle;
	}
}
