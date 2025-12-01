package Calc.commands;

import Calc.CalculatorFacade;

public class ChooseOperationCommand implements Command {

    private final CalculatorFacade facade;
    private final String operator;
    private String prevCurrent, prevPrevious, prevOp;

    public ChooseOperationCommand(CalculatorFacade facade, String operator) {
        this.facade = facade;
        this.operator = operator;
    }

    @Override
    public void execute() {
        prevCurrent  = facade.getCurrentDisplay();
        prevPrevious = facade.getPreviousDisplay();
        prevOp       = facade.getOperator();

        facade.chooseOperation(operator);
    }

    @Override
    public void undo() {
        facade.restoreState(prevCurrent, prevPrevious, prevOp);
    }
}

