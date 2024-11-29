package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.dto.BenefitDetailResponse;
import christmas.domain.dto.OrderDetailResponse;
import java.util.List;
import org.junit.jupiter.api.Test;

class BenefitsTest {

    @Test
    void 총_금액을_반환한다() {
        // given
        Benefit benefit1 = new Benefit(BenefitType.CHRISTMAS, 1000);
        Benefit benefit2 = new Benefit(BenefitType.SPECIAL, 2000);
        Benefits benefits = new Benefits(List.of(benefit1, benefit2), null);

        // when
        int totalAmount = benefits.getTotalAmount();

        // then
        assertThat(totalAmount).isEqualTo(3000);
    }

    @Test
    void 증정_혜택이_아닌_총_금액을_반환한다() {
        // given
        Benefit benefit1 = new Benefit(BenefitType.CHRISTMAS, 1000);
        Benefit benefit2 = new Benefit(BenefitType.PRESENTATION, 2000);
        Benefits benefits = new Benefits(List.of(benefit1, benefit2), null);

        // when
        int nonPresentationTotalAmount = benefits.getNonPresentationTotalAmount();

        // then
        assertThat(nonPresentationTotalAmount).isEqualTo(1000);
    }

    @Test
    void 증정_응답이_NULL일_경우_NULL을_반환한다() {
        // given
        Benefits benefits = new Benefits(List.of(), null);

        // when
        OrderDetailResponse result = benefits.getPresentationResponse();

        // then
        assertThat(result).isNull();
    }

    @Test
    void 혜택_응답_리스트를_반환한다() {
        // given
        Benefit benefit1 = new Benefit(BenefitType.CHRISTMAS, 1000);
        Benefit benefit2 = new Benefit(BenefitType.SPECIAL, 2000);
        Benefits benefits = new Benefits(List.of(benefit1, benefit2), null);

        // when
        List<BenefitDetailResponse> responses = benefits.getBenefitResponses();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).name()).isEqualTo(BenefitType.CHRISTMAS.getName());
        assertThat(responses.get(0).amount()).isEqualTo(1000);
        assertThat(responses.get(1).name()).isEqualTo(BenefitType.SPECIAL.getName());
        assertThat(responses.get(1).amount()).isEqualTo(2000);
    }
}