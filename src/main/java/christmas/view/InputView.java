package christmas.view;

import static christmas.constant.MessageConstant.VISIT_DATE;
import static christmas.validation.CommonValidator.validateIsNumeric;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readVisitDate() {
        System.out.println(VISIT_DATE.get());
        String input = Console.readLine();

        validateIsNumeric(input);
        return Integer.parseInt(input);
    }
}
