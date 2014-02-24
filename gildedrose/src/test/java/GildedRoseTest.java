import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class GildedRoseTest {

    @Test
    public void should_decrease_the_quality_and_sellin_days() {
        Item aNormalItem = new Item("A normal item", 10, 20);
        GildedRose gildedRose = new GildedRose(new Item[]{aNormalItem});
        
        gildedRose.updateItems();

        assertThat(aNormalItem.getSellIn(), is(9));
        assertThat(aNormalItem.getQuality(), is(19));
    }
}
