package christmas.domain;

import christmas.domain.dto.BenefitInfoResponse;
import christmas.domain.dto.OrderInfoResponse;
import java.util.List;

public class Benefits {

    private final List<Benefit> benefits;
    private final Order presentation;

    public Benefits(List<Benefit> benefits, Order presentation) {
        this.benefits = benefits;
        this.presentation = presentation;
    }

    public int getTotalAmount() {
        return benefits.stream()
                .filter(Benefit::isNotPresentation)
                .mapToInt(Benefit::getAmount)
                .sum();
    }

    public OrderInfoResponse getPresentationResponse() {
        if (presentation == null) {
            return null;
        }
        return presentation.createResponse();
    }

    public List<BenefitInfoResponse> getBenefitResponses() {
        return benefits.stream()
                .map(Benefit::createResponse)
                .toList();
    }
}
