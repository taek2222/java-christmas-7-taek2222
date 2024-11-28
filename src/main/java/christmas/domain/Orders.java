package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import christmas.domain.dto.OrderInfoResponse;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    private List<Order> orders;

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

    public List<OrderInfoResponse> createResponse() {
        return orders.stream()
                .map(Order::createResponse)
                .toList();
    }

    public int calculateTotalQuantity() {
        return orders.stream()
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public boolean isOnlyDrink() {
        long count = orders.stream()
                .filter(Order::isMenuTypeDrink)
                .count();

        return orders.size() == count;
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
