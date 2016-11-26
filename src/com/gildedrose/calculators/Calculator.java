package com.gildedrose.calculators;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.gildedrose.Item;
import com.gildedrose.ItemType;

public abstract class Calculator {

	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;
	protected static final int MIN_SELL_IN = 0;
	
	abstract ItemType getAcceptedItemType();
	
	public int calculateSellIn(Item item) {
		return item.sellIn - 1;
	}
	
	public abstract int calculateQuality(Item item);
	
	protected int increaseByNotExceedingMax(int quality, int increment) {
		return min(quality + increment, MAX_QUALITY);
	}

	protected int decreaseByNotExceedingMin(int quality, int decrement) {
		return max(quality - decrement, MIN_QUALITY);
	}
}
