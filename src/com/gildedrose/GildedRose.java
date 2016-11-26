package com.gildedrose;

import static java.util.Arrays.asList;

import com.gildedrose.calculators.Calculator;
import com.gildedrose.calculators.CalculatorProvider;

class GildedRose {
	Item[] items;
	
	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		asList(items).stream().forEach(this::updateItem);
	}

	private void updateItem(Item item) {
		Calculator calculator = CalculatorProvider.getByItemName(item.name);
		item.sellIn = calculator.calculateSellIn(item);
		item.quality = calculator.calculateQuality(item);
	}
}
