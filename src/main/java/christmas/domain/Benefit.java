package christmas.domain;

import static christmas.domain.BenefitType.PRESENTATION;

import christmas.domain.dto.BenefitInfoResponse;

public class Benefit {

    private final BenefitType type;
    private final int amount;

    public Benefit(BenefitType benefitType, int amount) {
        this.type = benefitType;
        this.amount = amount;
    }

    public boolean isNotPresentation() {
        return !type.equals(PRESENTATION);
    }

    public BenefitInfoResponse createResponse() {
        return new BenefitInfoResponse(type.getName(), amount);
    }

    public int getAmount() {
        return amount;
    }
}
