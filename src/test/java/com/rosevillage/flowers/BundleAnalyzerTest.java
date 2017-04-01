package com.rosevillage.flowers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cedarsoftware.util.DeepEquals;
import com.rosevillage.calculation.BundleAnalyzer;
import com.rosevillage.calculation.Solution;
import com.rosevillage.flowers.BaggedFlower;
import com.rosevillage.flowers.Bundle;
import com.rosevillage.helper.ParamHelper;

public class BundleAnalyzerTest {

	static BundleAnalyzer bunAnalyzer;

	Solution solution;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bunAnalyzer = new BundleAnalyzer();
	}

	@Test
	public void testSolveRose10() {

		List<Bundle> roseBundle = ParamHelper.getRoseBundle();
		solution = bunAnalyzer.solve(roseBundle, 10);
		assertEquals("Checking resulting amount for 10 Roses", new Double(12.99), solution.getTotalValue());
		assertEquals("Checking resulting size", 10, solution.getTotalSize().intValue());
		int[] expectedCount = { 1, 0 };
		assertBundleList(roseBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveRose12() {
		List<Bundle> roseBundle = ParamHelper.getRoseBundle();
		solution = bunAnalyzer.solve(roseBundle, 12);
		// As the 12 is indivisiable, only bundle of 10 will be created.
		assertTrue("Checking resulting amount for 12 Roses", solution.getTotalValue().equals(12.99));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(10));
		int[] expectedCount = { 1, 0 };
		assertBundleList(roseBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveRose35() {
		List<Bundle> roseBundle = ParamHelper.getRoseBundle();
		solution = bunAnalyzer.solve(roseBundle, 35);
		assertTrue("Checking resulting amount for 35 Roses", solution.getTotalValue().equals(45.96));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(35));
		int[] expectedCount = { 3, 1 };
		assertBundleList(roseBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveLilies15() {
		List<Bundle> liliesBundle = ParamHelper.getLiliesBundle();
		solution = bunAnalyzer.solve(liliesBundle, 15);
		assertTrue("Checking resulting amount for 15 Lilies", solution.getTotalValue().equals(41.90));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(15));
		int[] expectedCount = { 1, 1, 0 };
		assertBundleList(liliesBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveLilies21() {
		List<Bundle> liliesBundle = ParamHelper.getLiliesBundle();
		solution = bunAnalyzer.solve(liliesBundle, 21);
		assertTrue("Checking resulting amount for 21 Lilies", solution.getTotalValue().equals(58.85));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(21));
		int[] expectedCount = { 1, 2, 0 };
		assertBundleList(liliesBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveTulips13() {
		List<Bundle> tulipsBundle = ParamHelper.getTulipsBundle();
		solution = bunAnalyzer.solve(tulipsBundle, 13);
		assertTrue("Checking resulting amount for 13 Tulips", solution.getTotalValue().equals(25.85));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(13));
		int[] expectedCount = { 0, 2, 1 };
		assertBundleList(tulipsBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveTulips21() {
		List<Bundle> tulipsBundle = ParamHelper.getTulipsBundle();
		solution = bunAnalyzer.solve(tulipsBundle, 21);
		assertTrue("Checking resulting amount for 21 Tulips", solution.getTotalValue().equals(39.85));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(21));
		int[] expectedCount = { 2, 0, 1 };
		assertBundleList(tulipsBundle, expectedCount, solution.getBaggedFlowers());

	}

	@Test
	public void testSolveTulips55() {
		List<Bundle> tulipsBundle = ParamHelper.getTulipsBundle();
		solution = bunAnalyzer.solve(tulipsBundle, 55);
		assertTrue("Checking resulting amount for 55 Tulips", solution.getTotalValue().equals(104.65));
		assertTrue("Checking resulting size", solution.getTotalSize().equals(55));
		int[] expectedCount = { 5, 2, 0 };
		assertBundleList(tulipsBundle, expectedCount, solution.getBaggedFlowers());

	}

	private void assertBundleList(List<Bundle> bundles, int[] expectedCount, Collection<BaggedFlower> actual) {
		List<BaggedFlower> expected = new ArrayList<>();
		int counter = 0;
		for (Bundle bundle : bundles) {
			expected.add(new BaggedFlower(bundle, expectedCount[counter++]));
		}
		assertTrue("Checking resulted Bagged flowers with expected ones", DeepEquals.deepEquals(expected, actual));
	}

}
