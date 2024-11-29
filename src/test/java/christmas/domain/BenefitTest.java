package christmas.domain;

import static christmas.domain.BenefitType.CHRISTMAS;
import static christmas.domain.BenefitType.PRESENTATION;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.dto.BenefitDetailResponse;
import org.junit.jupiter.api.Test;

class BenefitTest {

    @Test
    void 증정_혜택이_아니면_TRUE를_반환한다() {
        // given
        Benefit benefit = new Benefit(CHRISTMAS, 1);

        // when
        boolean result = benefit.isNotPresentation();

        // then
        assertThat(result)
                .isTrue();
    }

    @Test
    void 증정_혜택일_경우_FALSE를_반환한다() {
        // given
        Benefit benefit = new Benefit(PRESENTATION, 1);

        // when
        boolean result = benefit.isNotPresentation();

        // then
        assertThat(result)
                .isFalse();
    }

    @Test
    void 증정_응답_객체를_생성한다() {
        // given
        BenefitType type = PRESENTATION;
        int amount = 1;
        Benefit benefit = new Benefit(type, amount);

        // when
        BenefitDetailResponse result = benefit.createResponse();

        // then
        assertThat(result.name())
                .isEqualTo(type.getName());
        assertThat(result.amount())
                .isEqualTo(amount);
    }
}