package christmas.domain;

import static christmas.global.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.dto.OrderDetailResponse;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void 수량이_최소값_미만일_경우_예외를_던진다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;

        // when & then
        assertThatThrownBy(() -> new Order(menu, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.get());
    }

    @Test
    void 메뉴_타입에_따라_수량을_반환한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        Order order = new Order(menu, 3);

        // when
        int quantity = order.getQuantityByMenuType(MenuType.MAIN);

        // then
        assertThat(quantity).isEqualTo(3);
    }

    @Test
    void 메뉴_타입이_다르면_수량을_0으로_반환한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        Order order = new Order(menu, 3);

        // when
        int quantity = order.getQuantityByMenuType(MenuType.DESSERT);

        // then
        assertThat(quantity).isEqualTo(0);
    }

    @Test
    void 주문_금액을_계산한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        Order order = new Order(menu, 2);

        // when
        int amount = order.calculateAmount();

        // then
        assertThat(amount).isEqualTo(menu.getPrice() * 2);
    }

    @Test
    void 주문_응답_객체를_생성한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        Order order = new Order(menu, 2);

        // when
        OrderDetailResponse response = order.createResponse();

        // then
        assertThat(response.name()).isEqualTo(menu.getName());
        assertThat(response.quantity()).isEqualTo(2);
    }
}