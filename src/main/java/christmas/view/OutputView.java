package christmas.view;

import static christmas.constant.MessageConstant.EVENT_NOTICE;
import static christmas.constant.MessageConstant.NEW_LINE;
import static christmas.constant.MessageConstant.ORDER_MENU;
import static christmas.constant.MessageConstant.ORDER_MENU_HEADER;
import static christmas.constant.MessageConstant.WELCOME;

import christmas.domain.dto.OrderInfoResponse;
import java.util.List;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(WELCOME.get());
    }

    public void printEventNoticeMessage(String date) {
        System.out.printf(EVENT_NOTICE.get(date));
        System.out.println(NEW_LINE.get());
    }

    public void printOrdersMenu(List<OrderInfoResponse> orderResponses) {
        System.out.println(ORDER_MENU_HEADER.get());
        orderResponses.forEach(this::printOrderMenu);
    }

    private void printOrderMenu(OrderInfoResponse orderResponse) {
        System.out.println(ORDER_MENU.get(
                orderResponse.name(),
                orderResponse.quantity()
        ));
    }
}
