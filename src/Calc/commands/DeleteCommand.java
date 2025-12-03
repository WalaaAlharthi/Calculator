package Calc.commands;

import Calc.*;

public class DeleteCommand implements Command {

    private final CalculatorContext ctx;
    private String prevCurrent;

    public DeleteCommand(CalculatorContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        CalculatorFacade facade = ctx.getFacade();
        prevCurrent = facade.getRawCurrent();
        ctx.getState().onDelete(ctx);
    }

    @Override
    public void undo() {
        ctx.getFacade().forceSetCurrent(prevCurrent);
    }
}
