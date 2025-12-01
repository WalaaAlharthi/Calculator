package Calc.commands;

import Calc.CalculatorFacade;

public class ToggleSignCommand implements Command {

    private final CalculatorFacade facade;
    private String previousState;

    public ToggleSignCommand(CalculatorFacade facade) {
        this.facade = facade;
    }

    @Override
    public void execute() {
        previousState = facade.getCurrentDisplay();
        facade.toggleSign();
    }

    @Override
    public void undo() {
        facade.forceSetCurrent(previousState);
    }
}
