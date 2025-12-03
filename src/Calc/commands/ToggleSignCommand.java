package Calc.commands;

import Calc.*;

public class ToggleSignCommand implements Command {

    private final CalculatorContext ctx;
    private String prevCurrent;

    public ToggleSignCommand(CalculatorContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        CalculatorFacade facade = ctx.getFacade();
        prevCurrent = facade.getRawCurrent();
        ctx.getState().onToggleSign(ctx);
    }

    @Override
    public void undo() {
        ctx.getFacade().forceSetCurrent(prevCurrent);
    }
}
