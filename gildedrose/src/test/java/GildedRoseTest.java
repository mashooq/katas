import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class GildedRoseTest {

    @Test
    public void decrease_the_quality_and_sellin_days() {
        Item aNormalItem = new Item("A normal item", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{aNormalItem});

        gildedRose.updateItems();

        assertThat(aNormalItem.getSellIn(), is(9));
        assertThat(aNormalItem.getQuality(), is(19));
    }

    @Test
    public void once_the_sellin_date_has_passed_quality_degrades_twice() {
        Item aNormalItem = new Item("A normal item", 0, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{aNormalItem});

        gildedRose.updateItems();

        assertThat(aNormalItem.getSellIn(), is(-1));
        assertThat(aNormalItem.getQuality(), is(8));
    }

    @Test
    public void aged_bre_increases_in_quality_the_older_it_gets() {
        Item brie = new Item("Aged Brie", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItems();

        assertThat(brie.getSellIn(), is(9));
        assertThat(brie.getQuality(), is(11));
    }

    @Test
    public void quality_of_an_item_is_never_more_than_50() {
        Item brie = new Item("Aged Brie", 10, 50);
        GildedRose gildedRose = new GildedRose(new Item[]{brie});

        gildedRose.updateItems();

        assertThat(brie.getSellIn(), is(9));
        assertThat(brie.getQuality(), is(50));
    }

    @Test
    public void sulfuras_never_has_to_be_sold_or_decreases_in_quality() {
        Item sulfras = new Item("Sulfuras, Hand of Ragnaros", 10, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{sulfras});

        gildedRose.updateItems();

        assertThat(sulfras.getSellIn(), is(10));
        assertThat(sulfras.getQuality(), is(10));
    }

    @Test
    public void back_stage_passes_increase_in_quality_as_sellin_date_decreases() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 20, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{backstagePass});

        gildedRose.updateItems();

        assertThat(backstagePass.getSellIn(), is(19));
        assertThat(backstagePass.getQuality(), is(11));
    }

    @Test
    public void back_stage_pass_quality_increases_by_2_when_10_days_or_less() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        Item anotherBackstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{backstagePass, anotherBackstagePass});

        gildedRose.updateItems();

        assertThat(backstagePass.getQuality(), is(12));
        assertThat(anotherBackstagePass.getQuality(), is(12));
    }

    @Test
    public void back_stage_pass_quality_increases_by_3_when_5_days_or_less() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        Item anotherBackstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{backstagePass, anotherBackstagePass});

        gildedRose.updateItems();

        assertThat(backstagePass.getQuality(), is(13));
        assertThat(anotherBackstagePass.getQuality(), is(13));
    }

    @Test
    public void back_stage_pass_quality_goes_to_0_when_concert_passes() {
        Item backstagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        GildedRose gildedRose = new GildedRose(new Item[]{backstagePass});

        gildedRose.updateItems();

        assertThat(backstagePass.getQuality(), is(0));
    }

}
