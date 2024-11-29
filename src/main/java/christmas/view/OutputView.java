package christmas.view;

import static christmas.global.constant.MessageConstant.AFTER_DISCOUNT_AMOUNT_HEADER;
import static christmas.global.constant.MessageConstant.AMOUNT;
import static christmas.global.constant.MessageConstant.BEFORE_DISCOUNT_AMOUNT_HEADER;
import static christmas.global.constant.MessageConstant.BENEFITS_AMOUNT_HEADER;
import static christmas.global.constant.MessageConstant.BENEFIT_CONTENTS_HEADER;
import static christmas.global.constant.MessageConstant.BENEFIT_DETAILS;
import static christmas.global.constant.MessageConstant.EVENT_BADGE;
import static christmas.global.constant.MessageConstant.EVENT_NOTICE;
import static christmas.global.constant.MessageConstant.MINUS_AMOUNT;
import static christmas.global.constant.MessageConstant.NEW_LINE;
import static christmas.global.constant.MessageConstant.NO_CONTENT;
import static christmas.global.constant.MessageConstant.ORDER_DETAILS;
import static christmas.global.constant.MessageConstant.ORDER_MENU_HEADER;
import static christmas.global.constant.MessageConstant.PRESENTATION_HEADER;
import static christmas.global.constant.MessageConstant.WELCOME;

import christmas.global.constant.MessageConstant;
import christmas.domain.dto.BenefitInfoResponse;
import christmas.domain.dto.OrderInfoResponse;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###");

    public void printWelcomeMessage() {
        System.out.println(WELCOME.get());
    }

    public void printEventNoticeMessage(String date) {
        System.out.printf(EVENT_NOTICE.get(date));
        System.out.println(NEW_LINE.get());
    }

    public void printOrdersMenu(List<OrderInfoResponse> orderResponses) {
        System.out.println(ORDER_MENU_HEADER.get());
        orderResponses.forEach(this::printOrderDetail);
    }

    public void printBeforeDiscountAmount(int amount) {
        String formattedAmount = PRICE_FORMAT.format(amount);

        printHeader(BEFORE_DISCOUNT_AMOUNT_HEADER);
        System.out.println(AMOUNT.get(formattedAmount));
    }

    public void printPresentationMenu(OrderInfoResponse response) {
        printHeader(PRESENTATION_HEADER);

        if (response == null) {
            System.out.println(NO_CONTENT.get());
            return;
        }
        printOrderDetail(response);
    }

    public void printBenefitContents(List<BenefitInfoResponse> responses) {
        printHeader(BENEFIT_CONTENTS_HEADER);

        if (responses.isEmpty()) {
            System.out.println(NO_CONTENT.get());
            return;
        }
        responses.forEach(this::printBenefitDetail);
    }

    public void printTotalBenefitAmount(int amount) {
        printHeader(BENEFITS_AMOUNT_HEADER);

        if (amount == 0) {
            System.out.println(NO_CONTENT.get());
            return;
        }

        String formattedAmount = PRICE_FORMAT.format(amount);
        System.out.println(MINUS_AMOUNT.get(formattedAmount));
    }

    public void printAfterDiscountAmount(int amount) {
        printHeader(AFTER_DISCOUNT_AMOUNT_HEADER);

        if (amount == 0) {
            System.out.println(NO_CONTENT.get());
            return;
        }

        String formattedAmount = PRICE_FORMAT.format(amount);
        System.out.println(AMOUNT.get(formattedAmount));
    }

    public void printEventBadge(String name) {
        printHeader(EVENT_BADGE);

        if (name == null) {
            System.out.println(NO_CONTENT.get());
            return;
        }

        System.out.println(name);
    }

    private void printBenefitDetail(BenefitInfoResponse response) {
        String formattedAmount = PRICE_FORMAT.format(response.amount());

        System.out.printf(BENEFIT_DETAILS.get(response.name(), formattedAmount));
        System.out.printf(NEW_LINE.get());
    }

    private void printHeader(MessageConstant benefitContentsHeader) {
        System.out.printf(NEW_LINE.get());
        System.out.println(benefitContentsHeader.get());
    }

    private void printOrderDetail(OrderInfoResponse orderResponse) {
        System.out.println(ORDER_DETAILS.get(orderResponse.name(), orderResponse.quantity()));
    }
}
