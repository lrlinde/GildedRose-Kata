package com.gildedrose.calculators;

import static com.gildedrose.ItemType.PASSES;

import com.gildedrose.Item;
import com.gildedrose.ItemType;

class PassesCalculator extends Calculator {

	private static final int SELL_IN_10_DAYS = 10;
	private static final int SELL_IN_5_DAYS = 5;

	@Override
	ItemType getAcceptedItemType() {
		return PASSES;
	}
	
	@Override
	public int calculateQuality(Item item) {
		int increment = (item.sellIn < MIN_SELL_IN? -item.quality : (item.sellIn < SELL_IN_5_DAYS? 3 : (item.sellIn < SELL_IN_10_DAYS? 2 : 1)));
		return increaseByNotExceedingMax(item.quality, increment);
	}
}
