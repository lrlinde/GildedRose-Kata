package com.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.gildedrose.ItemType.AGED_BRIE;
import static com.gildedrose.ItemType.NORMAL;
import static com.gildedrose.ItemType.PASSES;
import static com.gildedrose.ItemType.SULFURAS;
import static junitparams.JUnitParamsRunner.$;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class GildedRoseTest {

	Object[] params() {
		return $(
				$("GIVEN Aged Brie, WHEN update, THEN quality increases by one", AGED_BRIE, 1, 0, 0, 1),
				$("GIVEN Aged Brie, WHEN update, THEN quality is not higher than maximum", AGED_BRIE, 1, 50, 0, 50),
				$("GIVEN Aged Brie with quality over maximum, WHEN update, THEN quality is maximum", AGED_BRIE, 1, 51, 0, 50),
				$("GIVEN Aged Brie expired, WHEN update, THEN quality increases by two", AGED_BRIE, 0, 10, -1, 12),
//				$("GIVEN Sulfuras, WHEN update, THEN quality is 80", SULFURAS, 1, 0, 0, 80),
//				$("GIVEN Sulfuras expired, WHEN update, THEN quality is 80", SULFURAS, 0, 0, -1, 80),
				$("GIVEN Sulfuras, WHEN update, THEN quality is 80", SULFURAS, 1, 80, 1, 80),
				$("GIVEN Sulfuras expired, WHEN update, THEN quality is 80", SULFURAS, 0, 80, 0, 80),
				$("GIVEN Passes due in more than 10 days, WHEN update, THEN quality increases by one", PASSES, 11, 0, 10, 1),
				$("GIVEN Passes due in 10 or less days, WHEN update, THEN quality increases by two", PASSES, 10, 0, 9, 2),
				$("GIVEN Passes due in 5 or less days, WHEN update, THEN quality increases by three", PASSES, 5, 0, 4, 3),
				$("GIVEN Passes expired, WHEN update, THEN quality is zero", PASSES, 0, 10, -1, 0),
				$("GIVEN Passes due in more than 10 days AND quality is maximum, WHEN update, THEN quality is maximum", PASSES, 11, 50, 10, 50),
				$("GIVEN Passes due in 10 or less days AND quality is close to maximum, WHEN update, THEN quality is maximum", PASSES, 10, 49, 9, 50),
				$("GIVEN Passes due in 5 or less days AND quality is close maximum, WHEN update, THEN quality is maximum", PASSES, 5, 49, 4, 50),
				$("GIVEN any other item, WHEN update, THEN quality decreases by one", NORMAL, 1, 1, 0, 0),
				$("GIVEN any other item expired, WHEN update, THEN quality decreases by two", NORMAL, 0, 10, -1, 8),
				$("GIVEN any other item, WHEN update, THEN quality is not negative", NORMAL, 0, 1, -1, 0)
				);
	}

	@Test
	@Parameters(method = "params")
	public void test(String test, ItemType itemType, int sellIn, int quality, int expectedSellIn, int expectedQuality) {
		GildedRose app = new GildedRose(givenItems(new Item(itemType.getName(), sellIn, quality)));
		app.updateQuality();
		assertItem(app.items[0], expectedSellIn, expectedQuality);
	}
	
	@Test
	public void givenNoItems_whenUpdate_thenNothingHappens() {
		GildedRose app = new GildedRose(givenItems());
		app.updateQuality();
		assertThat(app.items.length, is(0));
	}
	
	@Test
	public void givenMoreThanOneItems_whenUpdate_thenAllItemsUpdated() {
		GildedRose app = new GildedRose(givenItems(new Item(NORMAL.getName(), 1, 1), new Item(NORMAL.getName(), 1, 1)));
		app.updateQuality();
		assertThat(app.items.length, is(2));
		assertItem(app.items[0], 0, 0);
		assertItem(app.items[1], 0, 0);
	}

	private Item[] givenItems(Item ...items) {
		return items;
	}
	
	private void assertItem(Item item, int expectedSellIn, int expectedQuality) {
		assertThat("sellIn", item.sellIn, is(expectedSellIn));
		assertThat("quality", item.quality, is(expectedQuality));
	}
}
