package christmas.service;

import static christmas.domain.BenefitType.CHRISTMAS;
import static christmas.domain.BenefitType.PRESENTATION;
import static christmas.domain.BenefitType.SPECIAL;
import static christmas.domain.BenefitType.WEEKDAYS;
import static christmas.domain.BenefitType.WEEKEND;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.MAIN;

import christmas.domain.Benefit;
import christmas.domain.BenefitType;
import christmas.domain.Benefits;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderDate;
import christmas.domain.Orders;
import java.util.ArrayList;
import java.util.List;

public class BenefitService {

    private static final int D_DAY_BASE = 1000;
    private static final int D_DAY_DAILY = 100;
    private static final int WEEKDAYS_AMOUNT = 2_023;
    private static final int WEEKEND_AMOUNT = 2_023;
    private static final int GIFT_EVENT_THRESHOLD = 120_000;
    private static final int SPECIAL_AMOUNT = 1_000;
    private static final Menu PRESENTATION_MENU = CHAMPAGNE;

    private List<Benefit> benefits;

    public Benefits generateBenefits(OrderDate orderDate, Orders orders) {
        benefits = new ArrayList<>();

        applyDDayBenefit(orderDate);
        applyWeekdaysBenefit(orderDate, orders);
        applyWeekendBenefit(orderDate, orders);
        applySpecialBenefit(orderDate);
        Order order = applyPresentationBenefit(orders);

        return new Benefits(benefits, order);
    }

    private void applyDDayBenefit(OrderDate orderDate) {
        Integer dDay = orderDate.calculateDDay();

        if (dDay != null) {
            int amount = D_DAY_BASE + (D_DAY_DAILY * dDay);
            addBenefit(CHRISTMAS, amount);
        }
    }

    private void applyWeekdaysBenefit(OrderDate orderDate, Orders orders) {
        int amount = orders.calculateQuantityByMenuType(DESSERT) * WEEKDAYS_AMOUNT;

        if (!orderDate.isWeekend() && amount != 0) {
            addBenefit(WEEKDAYS, amount);
        }
    }

    private void applyWeekendBenefit(OrderDate orderDate, Orders orders) {
        int amount = orders.calculateQuantityByMenuType(MAIN) * WEEKEND_AMOUNT;

        if (orderDate.isWeekend() && amount != 0) {
            addBenefit(WEEKEND, amount);
        }
    }

    private void applySpecialBenefit(OrderDate orderDate) {
        if (orderDate.isSpecialDay()) {
            addBenefit(SPECIAL, SPECIAL_AMOUNT);
        }
    }

    private Order applyPresentationBenefit(Orders orders) {
        if (orders.getTotalAmount() >= GIFT_EVENT_THRESHOLD) {
            addBenefit(PRESENTATION, PRESENTATION_MENU.getPrice());
            return new Order(PRESENTATION_MENU);
        }
        return null;
    }

    private void addBenefit(BenefitType benefitType, int amount) {
        Benefit newBenefit = new Benefit(benefitType.getName(), amount);
        benefits.add(newBenefit);
    }
}
