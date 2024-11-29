package christmas.domain;

import static christmas.global.constant.ErrorMessage.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OrderDateTest {

    @Test
    void 유효하지_않은_날짜일_경우_예외를_던진다() {
        // given
        int invalidDate = 32;

        // when & then
        assertThatThrownBy(() -> new OrderDate(invalidDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE.get());
    }

    @Test
    void 크리스마스_날짜를_생성한다() {
        // given
        int christmasDate = 25;

        // when
        OrderDate orderDate = new OrderDate(christmasDate);

        // then
        assertThat(orderDate.toString()).isEqualTo("25");
    }

    @Test
    void 특별한_날짜를_확인한다() {
        // given
        OrderDate orderDate = new OrderDate(25); // 크리스마스

        // when
        boolean isSpecialDay = orderDate.isSpecialDay();

        // then
        assertThat(isSpecialDay).isTrue();
    }

    @Test
    void 주말을_확인한다() {
        // given
        OrderDate orderDate = new OrderDate(30); // 2023년 12월 30일은 토요일

        // when
        boolean isWeekend = orderDate.isWeekend();

        // then
        assertThat(isWeekend).isTrue();
    }

    @Test
    void DDay를_계산한다() {
        // given
        OrderDate orderDate = new OrderDate(24);

        // when
        Integer dDay = orderDate.calculateDDay();

        // then
        assertThat(dDay).isEqualTo(23);
    }

    @Test
    void 크리스마스_이후의_DDay는_NULL을_반환한다() {
        // given
        OrderDate orderDate = new OrderDate(26);

        // when
        Integer dDay = orderDate.calculateDDay();

        // then
        assertThat(dDay).isNull();
    }
}