package christmas.domain;

public enum BenefitType {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEKDAYS("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("스페셜 할인"),
    PRESENTATION("증정 이벤트")
    ;

    private final String name;

    BenefitType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
