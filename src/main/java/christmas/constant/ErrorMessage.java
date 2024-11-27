package christmas.constant;

public enum ErrorMessage {
    INVALID_NUMERIC("숫자만 입력 가능합니다. 잘못된 입력 : (%s) "),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String PREFIX = "[ERROR] ";
    private static final String RETRY = "다시 입력해 주세요.";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return PREFIX + message + RETRY;
    }

    public String get(Object... value) {
        return PREFIX + String.format(message, value) + RETRY;
    }
}
