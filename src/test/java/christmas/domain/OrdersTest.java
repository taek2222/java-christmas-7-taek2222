package christmas.domain;

import static christmas.domain.Menu.T_BONE_STEAK;
import static christmas.domain.Menu.ZERO_COLA;
import static christmas.global.constant.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.dto.OrderDetailResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdersTest {

    private Orders orders;

    @BeforeEach
    void setUp() {
        orders = new Orders();
    }

    @Test
    void 주문을_추가한다() {
        // given
        Order order = new Order(T_BONE_STEAK, 2);

        // when
        orders.addOrder(order);

        // then
        assertThat(orders.calculateTotalOrderQuantity()).isEqualTo(2);
    }

    @Test
    void 중복된_주문을_추가할_때_예외를_던진다() {
        // given
        Order order = new Order(T_BONE_STEAK, 2);
        orders.addOrder(order);

        // when & then
        assertThatThrownBy(() -> orders.addOrder(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.get());
    }

    @Test
    void 총_금액을_계산한다() {
        // given
        orders.addOrder(new Order(T_BONE_STEAK, 2));
        orders.addOrder(new Order(ZERO_COLA, 3));

        // when
        int totalAmount = orders.getTotalAmount();

        // then
        assertThat(totalAmount).isEqualTo(T_BONE_STEAK.getPrice() * 2 + ZERO_COLA.getPrice() * 3);
    }

    @Test
    void 주문_응답_리스트를_생성한다() {
        // given
        orders.addOrder(new Order(T_BONE_STEAK, 2));
        orders.addOrder(new Order(ZERO_COLA, 3));

        // when
        List<OrderDetailResponse> responses = orders.createResponse();

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).name()).isEqualTo(T_BONE_STEAK.getName());
        assertThat(responses.get(0).quantity()).isEqualTo(2);
        assertThat(responses.get(1).name()).isEqualTo(ZERO_COLA.getName());
        assertThat(responses.get(1).quantity()).isEqualTo(3);
    }

    @Test
    void 음료만_주문했는지_확인한다() {
        // given
        orders.addOrder(new Order(ZERO_COLA, 3));

        // when
        boolean isOnlyDrink = orders.isOnlyDrink();

        // then
        assertThat(isOnlyDrink).isTrue();
    }

    @Test
    void 음료만_주문하지_않았는지_확인한다() {
        // given
        orders.addOrder(new Order(T_BONE_STEAK, 2));
        orders.addOrder(new Order(ZERO_COLA, 3));

        // when
        boolean isOnlyDrink = orders.isOnlyDrink();

        // then
        assertThat(isOnlyDrink).isFalse();
    }
}