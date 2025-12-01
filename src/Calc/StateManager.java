
package Calc;

public class StateManager {

    private String current = "";
    private String previous = "";
    private String operator = "";

    public String getCurrent() { return current; }
    public String getPrevious() { return previous; }
    public String getOperator() { return operator; }

   
    public boolean hasPendingOperation() {
        return !previous.isEmpty() && !operator.isEmpty();
    }

    public boolean canCompute() {
        return !previous.isEmpty() && !current.isEmpty() && !operator.isEmpty();
    }

    public void setCurrent(String value) {
        this.current = value;
    }

    public void clearAll() {
        current = "";
        previous = "";
        operator = "";
    }
    /** Called when user selects an operator (+, -, *, /, etc.) */
    public void startOperation(String op) {
        if (!current.isEmpty()) {
            previous = current;
            current = "";
            operator = op;
        }
    }
    /** Used after compute() finishes */
    public void applyResult(String result) {
        current = result;
        previous = "";
        operator = "";
    }
    /** Replace operator without touching numbers  */
    public void changeOperator(String newOp) {
        if (!previous.isEmpty()) {
            operator = newOp;
        }
    }
}
