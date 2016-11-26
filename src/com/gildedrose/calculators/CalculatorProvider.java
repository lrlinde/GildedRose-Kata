package com.gildedrose.calculators;

import static com.gildedrose.calculators.ItemType.findByName;
import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Map;

public class CalculatorProvider {

	private static Map<ItemType, Calculator> calculators = initCalculators();
	
	private static Map<ItemType, Calculator> initCalculators() {
		return collectToMapByItemName(new AgedBrieCalculator(), 
									  new SulfurasCalculator(), 
									  new PassesCalculator(), 
									  new NormalCalculator());
	}
	
	private static Map<ItemType, Calculator> collectToMapByItemName(Calculator... calculators) {
		return asList(calculators).stream().collect(toMap(Calculator::getAcceptedItemType, identity()));
	}
	
	public static Calculator getByItemName(String itemName) {
		return calculators.get(findByName(itemName));
	}
}
