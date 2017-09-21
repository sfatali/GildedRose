package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

// Example scenarios for testing
//   Item("+5 Dexterity Vest", 10, 20));
//   Item("Aged Brie", 2, 0));
//   Item("Elixir of the Mongoose", 5, 7));
//   Item("Sulfuras, Hand of Ragnaros", 0, 80));
//   Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
//   Item("Conjured Mana Cake", 3, 6));

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_10_11() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 10) );
		
		// Act
		store.updateEndOfDay();
		
		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(11, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_AgedBrie_Quality_not_exceeds_50() {
		// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Aged Brie", 2, 50) );

		// Act
		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Sulfuras_no_change_before_sellin() {// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", 2, 80) );

		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Sulfuras_no_change_after_sellin() {// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Sulfuras, Hand of Ragnaros", -2, 80) );

		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(80, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Random_item_quality_never_negative1() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Random item", 2, 0) );

		store.updateEndOfDay();

		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Random_item_quality_never_negative2() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Random item", -2, 0) );

		store.updateEndOfDay();

		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Random_item_before_sell_in() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Random item", 2, 10) );

		store.updateEndOfDay();

		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(9, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_Random_item_after_sell_in() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Random item", 0, 10) );

		store.updateEndOfDay();

		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(8, itemBrie.getQuality());
	}


	@Test
	public void testUpdateEndOfDay_backstage_passes_sellin_less_than_11() {
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) );

		store.updateEndOfDay();

		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(12, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_backstage_passes_sellin_less_than_6() {// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) );

		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(13, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_backstage_passes_concert_passed() {// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10) );

		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(0, itemBrie.getQuality());
	}

	@Test
	public void testUpdateEndOfDay_backstage_passes_quality_not_gteater_50() {// Arrange
		GildedRose store = new GildedRose();
		store.addItem(new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50) );

		store.updateEndOfDay();

		// Assert
		List<Item> items = store.getItems();
		Item itemBrie = items.get(0);
		assertEquals(50, itemBrie.getQuality());
	}
}
