package christmas.domain;

import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.MAIN;

public class Benefits {

    private static final int D_DAY_BASE = 1000;
    private static final int D_DAY_DAILY = 100;

    private final int DDay;
    private final int weekdays;
    private final int weekend;
    private final int special;

    public Benefits(OrderDate orderDate, Orders orders) {
        this.DDay = calculateDDayBenefit(orderDate);
        this.weekdays = calculateWeekdaysBenefit(orderDate, orders);
        this.weekend = calculateWeekendBenefit(orderDate, orders);
        this.special = calculateSpecialBenefit(orderDate);
    }

    private int calculateDDayBenefit(OrderDate orderDate) {
        Integer day = orderDate.calculateDDay();
        if (day == null) {
            return 0;
        }

        return D_DAY_BASE + (D_DAY_DAILY * day);
    }

    private int calculateWeekdaysBenefit(OrderDate orderDate, Orders orders) {
        if (orderDate.isWeekend())
            return 0;

        return orders.countMenuType(DESSERT) * 2_023;
    }

    private int calculateWeekendBenefit(OrderDate orderDate, Orders orders) {
        if (!orderDate.isWeekend())
            return 0;

        return orders.countMenuType(MAIN) * 2_023;
    }

    private int calculateSpecialBenefit(OrderDate orderDate) {
        if (orderDate.isSpecialDay())
            return 1_000;
        return 0;
    }
}
