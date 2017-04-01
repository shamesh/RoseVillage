package com.rosevillage.calculation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import com.rosevillage.flowers.BaggedFlower;
import com.rosevillage.flowers.Bundle;

/**
 * Finds appropriate bundles using Unbounded knapsack solver
 */
public class BundleAnalyzer {

	/**
	 * Solves the unbounded knapsack problem for a given collection of items.
	 *
	 * @param bundles
	 *            available items
	 * @param capacity
	 *            knapsack capacity (total)
	 */
	public Solution solve(List<Bundle> bundles, Integer capacity) {
		Integer normFactor = calculateNormalizationFactor(bundles, capacity);
		List<Bundle> normItems = normalizedItemsCopy(bundles, normFactor);
		Integer normCapacity = capacity / normFactor;

		Solution solution = dynamicProgrammingSolution(normItems, normCapacity);

		return denormalizedSolution(solution, normFactor);
	}

	/**
	 * Dynamic programming implementation based on <a href=
	 * "https://gist.github.com/sbilinski/9acdc05a9ad17c95eabf#file-unboundedknapsackdp-java"></a>.
	 */
	private Solution dynamicProgrammingSolution(List<Bundle> bundles, Integer capacity) {

		Collections.sort(bundles, (Bundle i1, Bundle i2) -> i2.getValue().compareTo(i1.getValue()));

		// Keeps track of current sack value for given capacity
		int[] sackValues = new int[capacity + 1];

		// Keeps track of items that "completed" a given capacity. That is,
		// lastItem[c] is the index of the item, that was
		// most recently added to the sack with capacity 'c'
		int[] lastItem = new int[capacity + 1];

		// There is no "last item" in a given capacity by default. We'll be
		// using a negative index to distinguish this
		// case (clarity).
		Arrays.fill(lastItem, -1);

		for (int i = 0; i < bundles.size(); i++) {
			Bundle bundle = bundles.get(i);
			int size = bundle.getSize();

			for (int c = size; c <= capacity; c++) {
				// The size is used instead of value as we need to choose the
				// maximum possible bundle size.
				// Choosing size will ensure that the largest flower bundle is
				// created minimizing total numbers of bundles.
				final int trial = sackValues[c - size] + size;

				if (sackValues[c] < trial) {
					sackValues[c] = trial;
					lastItem[c] = i;
				}
			}
		}

		List<BaggedFlower> baggedFlowers = new ArrayList<>();
		int[] counters = collectItemCounters(bundles, capacity, lastItem);
		for (int i = 0; i < bundles.size(); i++) {
			baggedFlowers.add(new BaggedFlower(bundles.get(i), counters[i]));
		}
		return new Solution(Collections.unmodifiableCollection(baggedFlowers));
	}

	/**
	 * A helper method for the dynamic programming solution, which maps the
	 * lastItem array to an array of counters.
	 */
	private int[] collectItemCounters(List<Bundle> bundles, Integer capacity, int[] lastItem) {
		int[] counters = new int[bundles.size()];
		int c = capacity;
		while (c > 0) {
			int itemIndex = lastItem[c];

			// If no item was added in this capacity, move to the previous one.
			while (itemIndex < 0 && c > 0) {
				c--;
				itemIndex = lastItem[c];
			}

			// If an item was found, increment it's counter and move "down" by
			// current item size.
			if (itemIndex >= 0 && c > 0) {
				counters[itemIndex]++;
				c = c - bundles.get(itemIndex).getSize();
			}
		}

		return counters;
	}

	/**
	 * Calculates the normalization factor for input data (to save memory during
	 * dp iterations)
	 */
	private Integer calculateNormalizationFactor(List<Bundle> bundles, Integer capacity) {
		BiFunction<Integer, Integer, Integer> gcd = (Integer a, Integer b) -> BigInteger.valueOf(a)
				.gcd(BigInteger.valueOf(b)).intValue();
		return bundles.stream().map(item -> {
			return item.getSize() == 0 ? capacity : gcd.apply(item.getSize(), capacity);
		}).reduce(capacity, Integer::min);
	}

	/**
	 * Creates a normalized copy of input items.
	 */
	private List<Bundle> normalizedItemsCopy(List<Bundle> bundles, int normFactor) {
		List<Bundle> normItems = new ArrayList<>(bundles.size());
		for (Bundle i : bundles) {
			normItems.add(new Bundle(i.getName(), i.getValue(), i.getSize() / normFactor));
		}
		return normItems;
	}

	/**
	 * Creates a denormalized copy of the solution.
	 */
	private Solution denormalizedSolution(Solution solution, Integer normFactor) {
		List<BaggedFlower> denormItems = solution.getBaggedFlowers().stream().map(baggedFlower -> {
			Bundle bundle = baggedFlower.getItem();
			return new BaggedFlower(new Bundle(bundle.getName(), bundle.getValue(), bundle.getSize() * normFactor),
					baggedFlower.getCounter());
		}).collect(Collectors.toList());

		return new Solution(Collections.unmodifiableCollection(denormItems));
	}

}