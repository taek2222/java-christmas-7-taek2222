package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static christmas.global.constant.ErrorMessage.INVALID_ORDER;

import org.junit.jupiter.api.Test;

class MenuTest {

    @Test
    void 이름으로_메뉴를_찾는다() {
        // given
        String menuName = "티본스테이크";

        // when
        Menu menu = Menu.findByName(menuName);

        // then
        assertThat(menu).isNotNull();
        assertThat(menu.getName()).isEqualTo(menuName);
    }

    @Test
    void 존재하지_않는_이름으로_메뉴를_찾을_때_예외를_던진다() {
        // given
        String invalidMenuName = "없는메뉴";

        // when & then
        assertThatThrownBy(() -> Menu.findByName(invalidMenuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER.get());
    }

    @Test
    void 메뉴_타입이_일치하는지_확인한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        MenuType menuType = MenuType.MAIN;

        // when
        boolean isSameType = menu.isSameMenuType(menuType);

        // then
        assertThat(isSameType).isTrue();
    }

    @Test
    void 메뉴_타입이_일치하지_않는지_확인한다() {
        // given
        Menu menu = Menu.T_BONE_STEAK;
        MenuType menuType = MenuType.DESSERT;

        // when
        boolean isSameType = menu.isSameMenuType(menuType);

        // then
        assertThat(isSameType).isFalse();
    }
}