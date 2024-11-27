package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

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
}
