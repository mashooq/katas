package redpencil;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductPriceTest {
    public static final int INITIAL_PRICE = 100;
    Product product;

    @Test
    public void shouldCreateAProductWithPrice() {
        assertThat(product.getPrice(), is(INITIAL_PRICE));
    }

    @Before
    public void setupProduct() {
        product = new Product(INITIAL_PRICE);
    }

    @Test
    public void shouldReduceProductPriceByAPercentage() {
        product.reducePriceByPercentage(23);
        assertThat(product.getPrice(), is((INITIAL_PRICE * (100 - 23) / 100)));
    }

    @Test
    public void shouldActivateRedPencilPromotionWhenReducedBy5PercentOrMore() {
        product.reducePriceByPercentage(6);
        assertThat(product.isRedPencilPromotionActive(), is(true));
    }

    @Test
    public void byDefaultRedPencilPromotionIsNotActive() {
        assertThat(product.isRedPencilPromotionActive(), is(false));
    }

    @Test
    public void stealDealIsOnlyAppliedToRedPencil() {
        product.reducePriceByPercentage(4);
        product.activateStealDealPromotion();
        assertThat(product.isStealDealPromotionActive(), is(false));
    }

    @Test
    public void first15DaysStealDealTakes15PercentOffPrice() {
        product.reducePriceByPercentage(7);
        product.activateStealDealPromotion();
        product.setElapsedDays(10);

        assertThat(product.getPrice(), is(79));
    }

    @Test
    public void  eleventhToThirtyDaysOfStealDealDealTakes30PercentOffPrice() {
        product.reducePriceByPercentage(7);
        product.activateStealDealPromotion();
        product.setElapsedDays(20);

        assertThat(product.getPrice(), is(65));
    }

    @Test
    public void  afterThirtyDaysOfStealDealTake30PercentOffOriginalPrice() {
        product.reducePriceByPercentage(7);
        product.activateStealDealPromotion();
        product.setElapsedDays(31);

        assertThat(product.getPrice(), is(40));
    }

    @Test
    public void  afterThirtyDaysOfStealDealRPPPromotionIsDeactivated() {
        product.reducePriceByPercentage(7);
        product.activateStealDealPromotion();
        product.setElapsedDays(31);

        assertThat(product.isRedPencilPromotionActive(), is(false));
    }

    @Test
    public void  increasingThePriceOfAProductOnStealDealHasNoEffect() {
        product.reducePriceByPercentage(7);
        product.activateStealDealPromotion();
        product.setElapsedDays(10);
        product.setPrice(120);

        assertThat(product.getPrice(), is(79));
    }
}
