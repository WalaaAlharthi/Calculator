
package Calc.commands;

import Calc.*;

public class ChooseOperationCommand implements Command {

    private final CalculatorContext ctx;
    private final String operator;

    private String prevCurrent, prevPrevious, prevOp;

    public ChooseOperationCommand(CalculatorContext ctx, String operator) {
        this.ctx = ctx;
        this.operator = operator;
    }

    @Override
    public void execute() {
        CalculatorFacade f = ctx.getFacade();
        prevCurrent  = f.getRawCurrent();
        prevPrevious = f.getRawPrevious();
        prevOp       = f.getRawOperator();

        ctx.getState().onOperation(ctx, operator);
    }

    @Override
    public void undo() {
        ctx.getFacade().restoreState(prevCurrent, prevPrevious, prevOp);
    }
}
