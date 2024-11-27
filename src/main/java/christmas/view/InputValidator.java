package christmas.view;

import static christmas.constant.ErrorMessage.INVALID_DATE;

public class InputValidator {
    private static final int MIN_DATE = 0;
    private static final int MAX_DATE = 32;

    public static void validateIsChristmasDate(int date) {
        if (MIN_DATE > date || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_DATE.get());
        }
    }
}
