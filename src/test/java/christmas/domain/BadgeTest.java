package christmas.domain;

import static christmas.domain.Badge.findBadgeByAmount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest(name = "혜택 금액: {0}, 예상 뱃지: {1}")
    @CsvSource({
            "5000, 별",
            "7500, 별",
            "10000, 트리",
            "15000, 트리",
            "20000, 산타",
            "25000, 산타",
    })
    void 총혜택_금액에_따라_알맞는_뱃지를_반환한다(int totalBenefit, String expected) {
        // when
        String result = findBadgeByAmount(totalBenefit);

        // then
        Assertions.assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void 알맞는_뱃지가_없을_경우_NULL을_반환한다() {
        // when
        String result = findBadgeByAmount(0);

        // then
        Assertions.assertThat(result)
                .isNull();
    }
}