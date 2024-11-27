package christmas.domain;

import static christmas.constant.ErrorMessage.INVALID_ORDER;
import static christmas.domain.MenuType.APPETIZER;
import static christmas.domain.MenuType.DESSERT;
import static christmas.domain.MenuType.DRINK;
import static christmas.domain.MenuType.MAIN;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
    TAPAS("타파스", 6_000, APPETIZER),
    CAESAR_SALAD("시저샐러드", 6_000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 6_000, MAIN),
    BARBECUE_RIBS("바비큐립", 6_000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 6_000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 6_000, DESSERT),
    ICE_CREAM("아이스크림", 6_000, DESSERT),

    ZERO_COLA("제로콜라", 6_000, DRINK),
    RED_WINE("레드와인", 6_000, DRINK),
    CHAMPAGNE("샴페인", 6_000, DRINK);

    private final String name;
    private final int price;
    private final MenuType menuType;

    Menu(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public Menu findByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(INVALID_ORDER.get())
                );
    }
}
