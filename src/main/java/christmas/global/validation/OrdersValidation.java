package christmas.global.validation;

import static christmas.global.constant.ErrorMessage.INVALID_ONLY_DRINK;
import static christmas.global.constant.ErrorMessage.INVALID_ORDER_QUANTITY;

import christmas.domain.Orders;

public class OrdersValidation {

    private static final int MAXIMUM_ORDER_QUANTITY = 20;

    public static void validateOrders(Orders orders) {
        validateOnlyDrink(orders);
        validateTotalQuantity(orders);
    }

    private static void validateOnlyDrink(Orders orders) {
        if (orders.isOnlyDrink()) {
            throw new IllegalArgumentException(INVALID_ONLY_DRINK.get());
        }
    }

    private static void validateTotalQuantity(Orders orders) {
        int quantity = orders.calculateTotalOrderQuantity();

        if (quantity > MAXIMUM_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_QUANTITY.get(MAXIMUM_ORDER_QUANTITY));
        }
    }
}
