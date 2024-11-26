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

    public List<OrderInfoResponse> createResponse() {
        return orders.stream()
                .map(Order::createResponse)
                .toList();
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
