package christmas.domain;

import christmas.domain.dto.BenefitInfoResponse;

public class Benefit {

    private final String name;
    private final int amount;

    public Benefit(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public BenefitInfoResponse createResponse() {
        return new BenefitInfoResponse(name, amount);
    }
}
