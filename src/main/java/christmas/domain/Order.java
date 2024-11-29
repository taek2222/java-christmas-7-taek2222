package christmas.domain;

import static christmas.global.constant.ErrorMessage.INVALID_ORDER;

import christmas.domain.dto.OrderDetailResponse;
import java.util.Objects;

public class Order {

    private static final int MINIMUM_QUANTITY = 1;

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    public Order(Menu menu) {
        this(menu, 1);
    }

    public int getQuantityByMenuType(MenuType menuType) {
        if (menu.isSameMenuType(menuType)) {
            return quantity;
        }
        return 0;
    }

    public int calculateAmount() {
        return menu.getPrice() * quantity;
    }

    public OrderDetailResponse createResponse() {
        return new OrderDetailResponse(menu.getName(), quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Order order = (Order) obj;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.get());
        }
    }
}
