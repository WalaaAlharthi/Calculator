package Calc.commands;

import Calc.*;

public class AppendNumberCommand implements Command {

    private final CalculatorContext ctx;
    private final String digit;
    private String prevCurrent;

    public AppendNumberCommand(CalculatorContext ctx, String digit) {
        this.ctx = ctx;
        this.digit = digit;
    }

    @Override
    public void execute() {
        CalculatorFacade facade = ctx.getFacade();
        prevCurrent = facade.getRawCurrent();   // القيمة الحقيقية
        ctx.getState().onNumber(ctx, digit);
    }

    @Override
    public void undo() {
        ctx.getFacade().forceSetCurrent(prevCurrent);
    }
}
