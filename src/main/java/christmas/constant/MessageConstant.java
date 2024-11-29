package christmas.constant;

public enum MessageConstant {
    // Output
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_NOTICE("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_HEADER("<주문 메뉴>"),
    BEFORE_DISCOUNT_AMOUNT_HEADER("<할인 전 총주문 금액>"),
    PRESENTATION_HEADER("<증정 메뉴>"),
    BENEFIT_CONTENTS_HEADER("<혜택 내역>"),
    BENEFITS_AMOUNT_HEADER("<총혜택 금액>"),
    AFTER_DISCOUNT_AMOUNT_HEADER("<할인 후 예상 결제 금액>"),
    ORDER_DETAILS("%s %d개"),
    AMOUNT("%s원"),
    MINUS_AMOUNT("-" + AMOUNT.get()),
    BENEFIT_DETAILS("%s: " + MINUS_AMOUNT.get()),

    NO_CONTENT("없음"),

    // Input
    VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),

    NEW_LINE(System.lineSeparator());

    private final String message;

    MessageConstant(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    public String get(Object... value) {
        return String.format(message, value);
    }
}
