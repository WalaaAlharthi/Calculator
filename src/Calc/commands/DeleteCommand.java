package Calc.commands;

import Calc.CalculatorFacade;

public class DeleteCommand implements Command {

    private final CalculatorFacade facade;
    private String previousState;

    public DeleteCommand(CalculatorFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        previousState = facade.getCurrentDisplay();
        facade.deleteLast();
    }

    @Override
    public void undo() {
        facade.forceSetCurrent(previousState);
    }
}
