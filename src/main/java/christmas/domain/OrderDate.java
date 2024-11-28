package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_DATE;

public class OrderDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CHRISTMAS_DAY = 25;

    private final int date;

    public OrderDate(int date) {
        validateIsChristmasDate(date);
        this.date = date;
    }

    public Integer calculateDDay() {
        if (date > CHRISTMAS_DAY)
            return null;
        return date;
    }

    @Override
    public String toString() {
        return String.valueOf(date);
    }

    private static void validateIsChristmasDate(int date) {
        if (MIN_DATE > date || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }
}
