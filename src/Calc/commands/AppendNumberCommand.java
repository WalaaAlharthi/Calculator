package Calc.commands;

import Calc.CalculatorFacade;

public class AppendNumberCommand implements Command {

    private final CalculatorFacade facade;
    private final String number;
    private String previousState;

    public AppendNumberCommand(CalculatorFacade facade, String number) {
        this.facade = facade;
        this.number = number;
    }

    @Override
    public void execute() {
        previousState = facade.getCurrentDisplay();
        facade.appendNumber(number);
    }

    @Override
    public void undo() {
        facade.forceSetCurrent(previousState);
    }
}
