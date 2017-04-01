package com.rosevillage.flowers;

import java.util.LinkedList;
import java.util.List;

public class Lilies extends Flower {

	public Lilies() {
		this.setName("Lilies");
		this.setCode("L09");
		this.setAvailableBundle(createBundle());

	}

	private List<Bundle> createBundle() {
		List<Bundle> bundle = new LinkedList<>();
		bundle.add(new Bundle("Lilies 9 Bunch", 24.95, 9));
		bundle.add(new Bundle("Lilies 6 Bunch", 16.95, 6));
		bundle.add(new Bundle("Lilies 3 Bunch", 9.95, 3));
		return bundle;
	}
}
