package christmas.domain;

import static christmas.domain.Badge.findBadgeByAmount;

public class Result {

    private final int totalAmount;
    private final int totalBenefitAmount;
    private final int totalNonPresentationBenefitAmount;

    public Result(Orders orders, Benefits benefits) {
        this.totalAmount = orders.getTotalAmount();
        this.totalBenefitAmount = benefits.getTotalAmount();
        this.totalNonPresentationBenefitAmount = benefits.getNonPresentationTotalAmount();
    }

    public int getAfterDiscountAmount() {
        return totalAmount - totalNonPresentationBenefitAmount;
    }

    public String getBadgeName() {
        return findBadgeByAmount(totalBenefitAmount);
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
