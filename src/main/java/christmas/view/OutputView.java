package christmas.view;

import static christmas.constant.MessageConstant.EVENT_NOTICE;
import static christmas.constant.MessageConstant.WELCOME;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(WELCOME.get());
    }

    public void printEventNoticeMessage(String date) {
        System.out.printf(EVENT_NOTICE.get(date));
    }
}
