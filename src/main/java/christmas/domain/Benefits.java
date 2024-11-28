package christmas.domain;

public class Benefits {

    private static final int D_DAY_BASE = 1000;
    private static final int D_DAY_DAILY = 100;

    private final int DDay;

    public Benefits(OrderDate orderDate) {
        this.DDay = calculateDDay(orderDate);
    }

    private int calculateDDay(OrderDate orderDate) {
        Integer day = orderDate.calculateDDay();
        if (day == null) {
            return 0;
        }

        return D_DAY_BASE + (D_DAY_DAILY * (day - 1));
    }
}
