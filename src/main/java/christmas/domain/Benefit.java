package christmas.domain;

import static christmas.domain.BenefitType.PRESENTATION;

import christmas.domain.dto.BenefitDetailResponse;

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

    public BenefitDetailResponse createResponse() {
        return new BenefitDetailResponse(type.getName(), amount);
    }

    public int getAmount() {
        return amount;
    }
}
