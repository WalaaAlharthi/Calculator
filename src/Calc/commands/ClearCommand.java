
package Calc.commands;

import Calc.*;

public class ClearCommand implements Command {

    private final CalculatorContext ctx;
    private String prevCurrent, prevPrevious, prevOp;

    public ClearCommand(CalculatorContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        CalculatorFacade f = ctx.getFacade();
        prevCurrent  = f.getRawCurrent();
        prevPrevious = f.getRawPrevious();
        prevOp       = f.getRawOperator();

        ctx.getState().onClear(ctx);
    }

    @Override
    public void undo() {
        ctx.getFacade().restoreState(prevCurrent, prevPrevious, prevOp);
    }
}
