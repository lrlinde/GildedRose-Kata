package com.gildedrose;


public enum ItemType {
	AGED_BRIE("Aged Brie"),
	SULFURAS("Sulfuras, Hand of Ragnaros"),
	PASSES("Backstage passes to a TAFKAL80ETC concert"),
	NORMAL("");
	
	private final String name;
	
	private ItemType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static ItemType findByName(String name) {
		for(ItemType i : values()) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return NORMAL;
	}
}

