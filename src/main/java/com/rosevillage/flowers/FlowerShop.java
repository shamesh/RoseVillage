package com.rosevillage.flowers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rosevillage.calculation.BundleAnalyzer;
import com.rosevillage.calculation.Solution;

/**
 * @author Shamesh Joshi 
 * This is Main class used to get customer input and show
 *         required bundle breakdown along with cost of flowers.
 *
 */
public class FlowerShop {
	private FlowerShop() {
	}

	public static void main(String[] args) {

		showWelcomePrompt();
		String splittor = ":";
		List<String> input = getInputFromClient();

		for (String line : input) {
			String[] data = line.split(splittor);
			int numberofFlowers = 0;
			boolean workAhead = true;
			if (data.length <= 1) {
				workAhead = false;
			}
			try {
				numberofFlowers = Integer.parseInt(data[0].trim());
			} catch (NumberFormatException e) {
				showMessage("Number of flowers should be Integer");
				workAhead = false;
			}
			if (!workAhead) {
				continue;
			}

			FlowerFactory ffact = new FlowerFactory();
			Flower flower = ffact.getFlower(data[1].trim());
			if (flower != null) {
				List<Bundle> bundles = flower.getAvailableBundle();
				BundleAnalyzer bunAnalyzer = new BundleAnalyzer();
				Solution solution = bunAnalyzer.solve(bundles, numberofFlowers);
				showResult(solution, flower, numberofFlowers);
			} else {
				showMessage("Please choose valid Flower code ");
			}
		}

	}

	private static void showResult(Solution solution, Flower flower, Integer numberofFlowers) {
		solution.getBaggedFlowers().forEach(bi -> {
			if (bi.getCounter() > 0) {
				showMessage(MessageFormat.format(
						"Flower: {0}, Bundle count: {1,number,#}, Flower count: {2,number,#}, Cost:{3,number,currency}",
						bi.getItem().getName(), bi.getCounter(), bi.getTotalSize(), bi.getTotalValue()));
			}
		});
		if (solution.getTotalSize() == numberofFlowers) {
			showMessage(MessageFormat.format("For Flower {0},  Total cost:{1,number,currency}", flower.getName(),
					solution.getTotalValue()));
		} else {
			showMessage(MessageFormat.format(
					"The available bundling cand not be done for requested {3,number,#} {0} flower "
							+ "\n We can bundle for {1,number,#} with total cost of {2,number,currency}",
					flower.getName(), solution.getTotalSize(), solution.getTotalValue(), numberofFlowers));
		}
	}

	private static void showMessage(String message) {
		System.out.println(message);

	}

	private static List<String> getInputFromClient() {

		List<String> input = new ArrayList<>();
		String in;
		Scanner scanner = new Scanner(System.in);
		do {
			in = scanner.next().trim();
			input.add(in);
		} while (!".".equals(in));
		scanner.close();
		return input;
	}

	private static void showWelcomePrompt() {
		String welcomemessage = "Welcome To RoseVillage Flower shop\n\n"
				+ "Rose (R12) -- For bundle of 5 and 10 : $6.99, $12.99\n"
				+ "Tulips (T58) -- For bundle of 3, 5 and 9 : $5.95, $9.95, $16.95\n"
				+ "Lilies (L09) -- For bundle of 3,6 and 9 : $9.95, $16.95, $24.95\n"
				+ "\nPlease enter Flower to Buy as:: <Number of flowers>:<Flower Code> " + "\nEnter . only to end";
		showMessage(welcomemessage);
	}

}
