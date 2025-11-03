package Calc;

/**
 * Facade class for Calculator logic.
 * It manages the operands, operation, and calculation.
 * UI should call these methods and then update the display.
 */
public class CalculatorFacade {

    private String currentOperand = "";
    private String previousOperand = "";
    private String operation = "";

     public String getCurrentDisplay() {
        return currentOperand;
    }

    public String getPreviousDisplay() {
        return previousOperand + " " + operation;
    }
    
    public void appendNumber(String number) {
        if (number.equals(".") && currentOperand.contains(".")) return;
        if (currentOperand.equals("0") && !number.equals(".")) currentOperand = "";
        currentOperand += number;
    }

    public void chooseOperation(String op) {
        if (!currentOperand.isEmpty()) {
            if (!previousOperand.isEmpty()) compute();
            operation = op;
            previousOperand = currentOperand;
            currentOperand = "";
        }
    }

    public void compute() {
        if (currentOperand.isEmpty() || previousOperand.isEmpty()) return;

        float curr = Float.parseFloat(currentOperand);
        float prev = Float.parseFloat(previousOperand);
        Operation operationObj = OperationFactory.getOperation(operation);
        if (operationObj == null) return;

        try {
            float result = operationObj.execute(prev, curr);
            currentOperand = (result - (int) result) != 0 ? Float.toString(result) : Integer.toString((int) result);
        } catch (ArithmeticException e) {
            currentOperand = "Error";
        }

        previousOperand = "";
        operation = "";
    }

    public void clear() {
        currentOperand = "";
        previousOperand = "";
        operation = "";
    }

    public void deleteLast() {
        if (!currentOperand.isEmpty()) {
            currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
        }
    }

    public void togglePlusMinus() {
        if (!currentOperand.isEmpty()) {
            float tmp = -Float.parseFloat(currentOperand);
            currentOperand = (tmp - (int) tmp) != 0 ? Float.toString(tmp) : Integer.toString((int) tmp);
        }
    }

  
}
