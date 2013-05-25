package redpencil;

public class Product {
    private int price;
    private int percentageReduction = 0;
    private boolean stealDealPromotionActive = false;
    private int elapsedDays;

    public Product(int initialPrice) {
        this.price = initialPrice;
    }


    public int getPrice() {
        int priceAfterDiscount = calculateDiscount(price, percentageReduction);
        if (stealDealPromotionActive) {
            priceAfterDiscount = calculateStealDealDiscount(priceAfterDiscount);
        }
        return priceAfterDiscount;
    }

    private int calculateStealDealDiscount(int priceAfterDiscount) {
        if (elapsedDays <= 10) {
            priceAfterDiscount = calculateDiscount(priceAfterDiscount, 15);
        } else if (elapsedDays <= 30) {
            priceAfterDiscount = calculateDiscount(priceAfterDiscount, 30);
        } else {
            priceAfterDiscount = calculateDiscount(price, 60);
        }
        return priceAfterDiscount;
    }

    private int calculateDiscount(int currentPrice, int reduction) {
        return currentPrice * (100 - reduction) / 100;
    }

    public void reducePriceByPercentage(int reduction) {
        this.percentageReduction = reduction;
    }

    public boolean isRedPencilPromotionActive() {
        return percentageReduction >= 5;
    }

    public void activateStealDealPromotion() {
        if (isRedPencilPromotionActive())
            this.stealDealPromotionActive = true;
    }


    public boolean isStealDealPromotionActive() {
        return stealDealPromotionActive;
    }


    public void setElapsedDays(int elapsedDays) {
        this.elapsedDays = elapsedDays;
    }
}