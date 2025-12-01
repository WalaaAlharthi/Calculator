package Calc;
public class CalculatorFacade {

    private final StateManager state = new StateManager();
    private final InputManager input = new InputManager();
    private final OperationManager operations = new OperationManager();
    private final DisplayManager display = new DisplayManager();

    // ----------------- UI Actions -----------------

    public void appendNumber(String num) {
        state.setCurrent(input.appendNumber(state.getCurrent(), num));
    }

    public void chooseOperation(String op) {
        // If an operation already exists, compute automatically
        if (state.hasPendingOperation()) {
            compute();
        }

        state.startOperation(op);
    }

    public void compute() {
        String result = operations.compute(state);
        state.applyResult(result);
    }

    public void deleteLast() {
        state.setCurrent(input.deleteLast(state.getCurrent()));
    }

    public void togglePlusMinus() {
        state.setCurrent(input.toggleSign(state.getCurrent()));
    }

    public void clear() {
        state.clearAll();
    }

    // ----------------- UI Display -----------------

    public String getCurrentDisplay() {
        return display.formatCurrent(state.getCurrent());
    }

    public String getPreviousDisplay() {
        return display.formatPrevious(
                state.getPrevious(),
                state.getOperator()
        );
    }
    public void showError() {
    state.setCurrent("Error");
    state.clearAll();
}
    public void forceSetCurrent(String value) {
    state.setCurrent(value);
}

public String getOperator() {
    return state.getOperator();
}

public void restoreState(String current, String previous, String operator) {
    state.setCurrent(current);
    state.setPrevious(previous);
    state.setOperator(operator);
}
public void toggleSign() {
    String value = state.getCurrent();
    if (value.startsWith("-")) {
        state.setCurrent(value.substring(1));       // -5 → 5
    } else if (!value.equals("0")) {
        state.setCurrent("-" + value);             // 5 → -5
    }
}

}

