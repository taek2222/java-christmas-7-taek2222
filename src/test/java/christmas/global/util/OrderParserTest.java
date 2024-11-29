package christmas.global.util;

import static christmas.global.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Menu;
import christmas.domain.Orders;
import org.junit.jupiter.api.Test;

class OrderParserTest {

    @Test
    void 유효한_입력을_파싱한다() {
        // given
        String input = "티본스테이크-2, 제로콜라-3";

        // when
        Orders orders = OrderParser.parseOrders(input);

        // then
        assertThat(orders.calculateTotalOrderQuantity()).isEqualTo(5);
        assertThat(orders.getTotalAmount()).isEqualTo(Menu.T_BONE_STEAK.getPrice() * 2 + Menu.ZERO_COLA.getPrice() * 3);
    }

    @Test
    void 유효하지_않은_입력을_파싱할_때_예외를_던진다() {
        // given
        String invalidInput = "티본스테이크-abc";

        // when & then
        assertThatThrownBy(() -> OrderParser.parseOrders(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.get());
    }

    @Test
    void 유효하지_않은_형식의_입력을_파싱할_때_예외를_던진다() {
        // given
        String invalidInput = "티본스테이크-2, 콜라";

        // when & then
        assertThatThrownBy(() -> OrderParser.parseOrders(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.get());
    }
}