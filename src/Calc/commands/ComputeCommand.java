
package Calc.commands;

import Calc.*;

public class ComputeCommand implements Command {

    private final CalculatorContext ctx;
    private String prevCurrent, prevPrevious, prevOp;

    public ComputeCommand(CalculatorContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        CalculatorFacade f = ctx.getFacade();
        prevCurrent  = f.getRawCurrent();
        prevPrevious = f.getRawPrevious();
        prevOp       = f.getRawOperator();

        ctx.getState().onEquals(ctx);
    }

    @Override
    public void undo() {
        ctx.getFacade().restoreState(prevCurrent, prevPrevious, prevOp);
    }
}
