package christmas.domain;

import static christmas.global.constant.ErrorMessage.INVALID_DATE;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class OrderDate {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CHRISTMAS_DAY = 25;

    private final LocalDate date;
    private final DayOfWeek dayOfWeek;

    public OrderDate(int date) {
        validateIsChristmasDate(date);
        this.date = LocalDate.of(2023, 12, date);
        this.dayOfWeek = this.date.getDayOfWeek();
    }

    public boolean isSpecialDay() {
        return dayOfWeek.getValue() == 7 || date.getDayOfMonth() == CHRISTMAS_DAY;
    }

    public boolean isWeekend() {
        return dayOfWeek.getValue() == 6 || dayOfWeek.getValue() == 5;
    }

    public Integer calculateDDay() {
        int dayOfMonth = date.getDayOfMonth();
        if (dayOfMonth > CHRISTMAS_DAY) {
            return null;
        }

        return dayOfMonth - 1;
    }

    @Override
    public String toString() {
        return String.valueOf(date.getDayOfMonth());
    }

    private static void validateIsChristmasDate(int date) {
        if (MIN_DATE > date || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }
}
