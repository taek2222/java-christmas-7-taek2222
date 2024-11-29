package christmas.domain;

import static christmas.domain.MenuType.DRINK;
import static christmas.global.constant.ErrorMessage.INVALID_ORDER;

import christmas.domain.dto.OrderDetailResponse;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        validateDuplication(order);
        orders.add(order);
    }

    public int getTotalAmount() {
        return orders.stream()
                .mapToInt(Order::calculateAmount)
                .sum();
    }

    public List<OrderDetailResponse> createResponse() {
        return orders.stream()
                .map(Order::createResponse)
                .toList();
    }

    public boolean isOnlyDrink() {
        return calculateTotalOrderQuantity() == calculateQuantityByMenuType(DRINK);
    }

    public int calculateTotalOrderQuantity() {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public int calculateQuantityByMenuType(MenuType menuType) {
        return orders.stream()
                .mapToInt(order -> order.getQuantityByMenuType(menuType))
                .sum();
    }

    private void validateDuplication(Order newOrder) {
        orders.stream()
                .filter(newOrder::equals)
                .findFirst()
                .ifPresent(order -> {
                    throw new IllegalArgumentException(INVALID_ORDER.get());
                });
    }
}
