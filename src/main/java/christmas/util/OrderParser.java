package christmas.util;

import static christmas.constant.ErrorMessage.INVALID_ORDER;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {

    private static final String REGEX = "^(.+)-([0-9]+)$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static Orders parseOrders(String input) {
        Orders orders = new Orders();

        Arrays.stream(input.split(","))
                .map(String::trim)
                .map(OrderParser::parseOrder)
                .forEach(orders::addOrder);

        return orders;
    }

    private static Order parseOrder(String input) {
        Matcher matcher = PATTERN.matcher(input);
        validateMatcher(matcher);
        return createOrder(matcher);
    }

    private static Order createOrder(Matcher matcher) {
        String menuName = matcher.group(1);
        int quantity = Integer.parseInt(matcher.group(2));
        Menu menu = Menu.findByName(menuName);

        return new Order(menu, quantity);
    }

    private static void validateMatcher(Matcher matcher) {
        if (!matcher.matches() || matcher.groupCount() != 2) {
            throw new IllegalArgumentException(INVALID_ORDER.get());
        }
    }
}
