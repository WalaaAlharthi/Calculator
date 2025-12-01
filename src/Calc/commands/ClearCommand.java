package Calc.commands;

import Calc.CalculatorFacade;

public class ClearCommand implements Command {

    private final CalculatorFacade facade;
    private String prevCurrent, prevPrevious, prevOp;

    public ClearCommand(CalculatorFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        prevCurrent  = facade.getCurrentDisplay();
        prevPrevious = facade.getPreviousDisplay();
        prevOp       = facade.getOperator();
        facade.clear();
    }

    @Override
    public void undo() {
        facade.restoreState(prevCurrent, prevPrevious, prevOp);
    }
}

