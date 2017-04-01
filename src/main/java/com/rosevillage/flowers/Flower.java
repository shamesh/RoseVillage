package com.rosevillage.flowers;

import java.util.List;

public abstract class Flower {
	private String name;
	private String code;
	private List<Bundle> availableBundle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Bundle> getAvailableBundle() {
		return availableBundle;
	}

	public void setAvailableBundle(List<Bundle> bundle) {
		this.availableBundle = bundle;
	}
}
