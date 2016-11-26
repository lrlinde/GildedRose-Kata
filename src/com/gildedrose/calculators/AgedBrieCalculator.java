package com.gildedrose.calculators;

import static com.gildedrose.calculators.ItemType.AGED_BRIE;

import com.gildedrose.Item;

class AgedBrieCalculator extends Calculator {

	@Override
	ItemType getAcceptedItemType() {
		return AGED_BRIE;
	}
	
	@Override
	public int calculateQuality(Item item) {
		int increment = item.sellIn < MIN_SELL_IN? 2 : 1;
		return increaseByNotExceedingMax(item.quality, increment);
	}
}
