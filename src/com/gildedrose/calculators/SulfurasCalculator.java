package com.gildedrose.calculators;

import static com.gildedrose.calculators.ItemType.SULFURAS;

import com.gildedrose.Item;

class SulfurasCalculator extends Calculator {

	@Override
	ItemType getAcceptedItemType() {
		return SULFURAS;
	}
	
	@Override
	public int calculateSellIn(Item item) {
		return item.sellIn;
	}

	@Override
	public int calculateQuality(Item item) {
		return item.quality;
	}
}
