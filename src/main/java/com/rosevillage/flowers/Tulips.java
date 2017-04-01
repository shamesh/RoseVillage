package com.rosevillage.flowers;

import java.util.LinkedList;
import java.util.List;

public class Tulips extends Flower {

	public Tulips() {
		this.setName("Tulips");
		this.setCode("T58");
		this.setAvailableBundle(createBundle());

	}

	private List<Bundle> createBundle() {
		List<Bundle> bundle = new LinkedList<>();
		bundle.add(new Bundle("Tulips 9 Bunch", 16.95, 9));
		bundle.add(new Bundle("Tulips 5 Bunch", 9.95, 5));
		bundle.add(new Bundle("Tulips 3 Bunch", 5.95, 3));
		return bundle;
	}
}
