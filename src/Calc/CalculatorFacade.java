package Calc;

public class CalculatorFacade {

    private final StateManager state = new StateManager();
    private final InputManager input = new InputManager();
    private final OperationManager operations = new OperationManager();
    private final DisplayManager display = new DisplayManager();

    // -----------------------------------------------------
    //                 UI ACTIONS
    // -----------------------------------------------------

    /** Append number or dot to current input */
    public void appendNumber(String num) {
        state.setCurrent(input.appendNumber(state.getCurrent(), num));
    }

    /** Choose operator (+, -, ×, ÷, %, ^, log, root) */
    public void chooseOperation(String op) {

        String prev = state.getPrevious();
        String curr = state.getCurrent();
        String lastOp = state.getOperator();

        // لو ما فيه ولا رقم إلى الآن، تجاهلي العملية
        if (prev.isEmpty() && curr.isEmpty()) {
            return;
        }

        // لو فيه رقم حالي، نضيفه للتعبير
        if (!curr.isEmpty()) {
            if (prev.isEmpty()) {
                // أول رقم في التعبير
                prev = curr;
            } else if (!lastOp.isEmpty()) {
                // نكمل التعبير: prev + lastOp + curr  (مثلاً: 5+2 أو 5+2×3)
                prev = prev + lastOp + curr;
            } else {
                // حالة احتياطية نادرًا ما تصير
                prev = prev + curr;
            }

            state.setPrevious(prev);
            state.setCurrent("");
        }

        // نخزن العملية الجديدة كـ operator الحالي
        state.setOperator(op);
    }

    /** Compute full expression previous op current */
    public void compute() {
    try {
        String prev = getPreviousDisplay();
        String curr = getCurrentDisplay();

        String result = operations.compute(state);

        state.applyResult(result);

        addToHistory(prev + " " + curr + " = " + result);

    } catch (Exception e) {
        showError();
    }
}


    /** Delete last digit */
    public void deleteLast() {
        state.setCurrent(input.deleteLast(state.getCurrent()));
    }

    /** Toggle sign: + / - باستخدام InputManager */
    public void togglePlusMinus() {
        state.setCurrent(input.toggleSign(state.getCurrent()));
    }

    /** Clear all state */
    public void clear() {
        state.clearAll();
    }

    /** Used by ToggleSignCommand (manual control) */
    public void toggleSign() {
        String value = state.getCurrent();
        if (value.startsWith("-")) {
            state.setCurrent(value.substring(1));
        } else if (!value.equals("0") && !value.isEmpty()) {
            state.setCurrent("-" + value);
        }
    }

    // -----------------------------------------------------
    //                     DISPLAY
    // -----------------------------------------------------

    public String getCurrentDisplay() {
        return display.formatCurrent(state.getCurrent());
    }

    public String getPreviousDisplay() {
        return display.formatPrevious(
                state.getPrevious(),
                state.getOperator()
        );
    }

    public String getOperator() {
        return state.getOperator();
    }

    // -----------------------------------------------------
    //                     ERROR HANDLING
    // -----------------------------------------------------

    public void showError() {
        state.setCurrent("Error");
        state.clearAll();   // يمنع مشاكل لاحقة
    }

    // -----------------------------------------------------
    //                     UNDO SUPPORT
    // -----------------------------------------------------

    /** Forcefully set current without affecting operator/previous */
    public void forceSetCurrent(String value) {
        state.setCurrent(value);
    }

    /** Restore full previous state (current, previous, operator) */
    public void restoreState(String current, String previous, String operator) {
        state.setCurrent(current);
        state.setPrevious(previous);
        state.setOperator(operator);
    }

    // -----------------------------------------------------
    //                 RAW STATE FOR COMMANDS
    // -----------------------------------------------------

    /** Raw current value without formatting (important for UNDO/REDO) */
    public String getRawCurrent() {
        return state.getCurrent();
    }

    /** Raw previous value without formatting */
    public String getRawPrevious() {
        return state.getPrevious();
    }

    /** Raw operator value */
    public String getRawOperator() {
        return state.getOperator();
    }

    public void setOperatorRaw(String op) {
        state.setOperator(op);
    }

    public void setPreviousRaw(String v) {
        state.setPrevious(v);
    }
    public void memoryAdd() {
    try {
        float v = Float.parseFloat(state.getCurrent());
        state.setMemory(state.getMemory() + v);
    } catch (Exception ignore) {}
}

public void memorySubtract() {
    try {
        float v = Float.parseFloat(state.getCurrent());
        state.setMemory(state.getMemory() - v);
    } catch (Exception ignore) {}
}

public void memoryRecall() {
    float v = state.getMemory();
    state.setCurrent(String.valueOf(v));
}

public void memoryClear() {
    state.clearMemory();
}
public java.util.List<String> getHistory() {
    return state.getHistory();
}

public void addToHistory(String exp) {
    state.addHistory(exp);
}


}
