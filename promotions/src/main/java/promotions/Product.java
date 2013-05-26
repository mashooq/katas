package promotions;

public class Product {
    private int price;
    private int percentageReduction = 0;
    private boolean stealDealPromotionActive = false;
    private int elapsedDays;
    private int priceReductionElapsedDays;

    public Product(int initialPrice) {
        this.price = initialPrice;
    }


    public int getPrice() {
        int priceAfterDiscount = price;
        if (elapsedDays <= 30) {
            priceAfterDiscount = calculateDiscount(price, percentageReduction);
        }

        if (stealDealPromotionActive) {
            priceAfterDiscount = calculateStealDealDiscount(priceAfterDiscount);
        }
        return priceAfterDiscount;
    }

    public void setPriceReductionElapsedDays(int priceReductionElapsedDays) {
        this.priceReductionElapsedDays = priceReductionElapsedDays;
    }

    private int calculateStealDealDiscount(int priceAfterDiscount) {
        if (elapsedDays <= 10) {
            priceAfterDiscount = calculateDiscount(priceAfterDiscount, 15);
        } else if (elapsedDays <= 30) {
            priceAfterDiscount = calculateDiscount(priceAfterDiscount, 30);
        } else {
            priceAfterDiscount = calculateDiscount(priceAfterDiscount, 60);
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
        return percentageReduction >= 5 && priceReductionElapsedDays > 5
                && elapsedDays <= 30;
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

    public void setPrice(int price) {
        if (!isStealDealPromotionActive())
            this.price = price;
    }
}