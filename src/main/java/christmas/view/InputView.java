package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.constant.MessageConstant.ORDER;
import static christmas.constant.MessageConstant.VISIT_DATE;
import static christmas.validation.CommonValidator.validateIsNumeric;

public class InputView {

    public int readVisitDate() {
        System.out.println(VISIT_DATE.get());
        String input = readLine();

        validateIsNumeric(input);
        return Integer.parseInt(input);
    }

    public String readOrder() {
        System.out.println(ORDER.get());
        return readLine();
    }
}
