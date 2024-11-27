package christmas.domain;

import java.util.List;

public class Orders {

    private List<Order> orders;

    public void addOrder(Order order) {
        orders.add(order);
    }
}
