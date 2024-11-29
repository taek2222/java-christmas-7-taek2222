package christmas.domain;

import christmas.domain.dto.BenefitDetailResponse;
import christmas.domain.dto.OrderDetailResponse;
import java.util.List;

public class Benefits {

    private final List<Benefit> benefits;
    private final Order presentation;

    public Benefits(final List<Benefit> benefits, final Order presentation) {
        this.benefits = benefits;
        this.presentation = presentation;
    }

    public int getTotalAmount() {
        return benefits.stream()
                .mapToInt(Benefit::getAmount)
                .sum();
    }

    public int getNonPresentationTotalAmount() {
        return benefits.stream()
                .filter(Benefit::isNotPresentation)
                .mapToInt(Benefit::getAmount)
                .sum();
    }

    public OrderDetailResponse getPresentationResponse() {
        if (presentation == null) {
            return null;
        }
        return presentation.createResponse();
    }

    public List<BenefitDetailResponse> getBenefitResponses() {
        return benefits.stream()
                .map(Benefit::createResponse)
                .toList();
    }
}
