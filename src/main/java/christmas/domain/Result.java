package christmas.domain;

public class Result {

    private final int totalAmount;
    private final int totalBenefitAmount;

    public Result(Orders orders, Benefits benefits) {
        this.totalAmount = orders.getTotalAmount();
        this.totalBenefitAmount = benefits.getTotalAmount();
    }

    public int getAfterDiscountAmount() {
        return totalAmount - totalBenefitAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
