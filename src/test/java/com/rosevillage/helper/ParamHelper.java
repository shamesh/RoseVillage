package com.rosevillage.helper;

import java.util.List;

import com.rosevillage.flowers.Bundle;
import com.rosevillage.flowers.Lilies;
import com.rosevillage.flowers.Rose;
import com.rosevillage.flowers.Tulips;

/**
 * @author Shamesh Joshi 
 * Helps to create required flower bundle.
 */
public class ParamHelper {
	public static List<Bundle> getRoseBundle() {
		Rose rose = new Rose();
		return rose.getAvailableBundle();
	}

	public static List<Bundle> getLiliesBundle() {
		Lilies lilies = new Lilies();
		return lilies.getAvailableBundle();
	}

	public static List<Bundle> getTulipsBundle() {
		Tulips tulips = new Tulips();
		return tulips.getAvailableBundle();
	}

}
