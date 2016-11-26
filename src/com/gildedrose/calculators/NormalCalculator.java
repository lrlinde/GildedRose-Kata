package com.gildedrose.calculators;

import static com.gildedrose.ItemType.NORMAL;

import com.gildedrose.Item;
import com.gildedrose.ItemType;

class NormalCalculator extends Calculator {

	@Override
	ItemType getAcceptedItemType() {
		return NORMAL;
	}
	
	@Override
	public int calculateQuality(Item item) {
		int decrement = item.sellIn < MIN_SELL_IN? 2 : 1;
		return decreaseByNotExceedingMin(item.quality, decrement);
	}
}
