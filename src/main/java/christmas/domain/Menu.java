package christmas.domain;

import static christmas.domain.MenuType.APPETIZER;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.DRINK;
import static christmas.domain.MenuType.MAIN;
import static christmas.global.constant.ErrorMessage.INVALID_ORDER;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 5_500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 55_000, MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
    ICE_CREAM("아이스크림", 5_000, DESSERT),

    ZERO_COLA("제로콜라", 3_000, DRINK),
    RED_WINE("레드와인", 60_000, DRINK),
    CHAMPAGNE("샴페인", 25_000, DRINK)
    ;

    private final String name;
    private final int price;
    private final MenuType type;

    Menu(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu findByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_ORDER.get())
                );
    }

    public boolean isSameMenuType(MenuType type) {
        return this.type == type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
