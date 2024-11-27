package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import christmas.domain.dto.OrderInfoResponse;
import java.util.Objects;

public class Order {

    private static final int MINIMUM_QUANTITY = 1;

    private Menu menu;
    private int quantity;

    public Order(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.get());
        }
    }

    public OrderInfoResponse createResponse() {
        return new OrderInfoResponse(
                menu.getName(),
                quantity
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Order order = (Order) obj;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu);
    }
}
