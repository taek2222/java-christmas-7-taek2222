package christmas.controller;

import static christmas.domain.Badge.findBadgeByAmount;

import christmas.domain.Benefits;
import christmas.domain.OrderDate;
import christmas.domain.Orders;
import christmas.domain.dto.BenefitInfoResponse;
import christmas.domain.dto.OrderInfoResponse;
import christmas.service.BenefitService;
import christmas.util.OrderParser;
import christmas.validation.OrdersValidation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BenefitService benefitService;

    public ChristmasController(InputView inputView, OutputView outputView, BenefitService benefitService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefitService = benefitService;
    }

    public void run() {
        outputView.printWelcomeMessage();

        OrderDate orderDate = readOrderDate();
        Orders orders = readOrders();
        Benefits benefits = generateBenefits(orderDate, orders);

        displayOrderDetails(orderDate, orders);
        displayEventDetails(orders, benefits);
    }

    private void displayOrderDetails(OrderDate orderDate, Orders orders) {
        outputView.printEventNoticeMessage(orderDate.toString());
        outputView.printOrdersMenu(orders.createResponse());
        outputView.printBeforeDiscountAmount(orders.getTotalAmount());
    }

    private OrderDate readOrderDate() {
        try {
            int date = inputView.readVisitDate();
            return new OrderDate(date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readOrderDate();
        }
    }

    private Orders readOrders() {
        try {
            String input = inputView.readOrder();
            Orders orders = OrderParser.parseOrders(input);
            OrdersValidation.validateOrders(orders);

            return orders;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readOrders();
        }
    }

    private void displayEventDetails(Orders orders, Benefits benefits) {
        displayPresentationMenu(benefits);
        displayBenefitContents(benefits);
        outputView.printTotalBenefitAmount(benefits.getTotalAmount());
        displayAfterDiscountAmount(orders, benefits);
        displayEventBadge(benefits);
    }

    private void displayEventBadge(Benefits benefits) {
        String badge = findBadgeByAmount(benefits.getTotalAmount());
        outputView.printEventBadge(badge);
    }

    private void displayAfterDiscountAmount(Orders orders, Benefits benefits) {
        int amount = orders.getTotalAmount() - benefits.getNonPresentationTotalAmount();
        outputView.printAfterDiscountAmount(amount);
    }

    private void displayBenefitContents(Benefits benefits) {
        List<BenefitInfoResponse> benefit = benefits.getBenefitResponses();
        outputView.printBenefitContents(benefit);
    }

    private void displayPresentationMenu(Benefits benefits) {
        OrderInfoResponse response = benefits.getPresentationResponse();
        outputView.printPresentationMenu(response);
    }

    private Benefits generateBenefits(OrderDate date, Orders orders) {
        return benefitService.generateBenefits(date, orders);
    }
}
