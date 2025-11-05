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

    // ------------------ Getter / Setter ------------------
    public String getCurrentDisplay() {
        return currentOperand;
    }

    public String getPreviousDisplay() {
        return previousOperand + " " + operation;
    }

    public void setCurrentDisplay(String value) {
        currentOperand = value;
    }

    public void clearPrevious() {
        previousOperand = "";
        operation = "";
    }

    // ------------------ Basic Input Handling ------------------
    public void appendNumber(String number) {
        if (number.equals(".") && currentOperand.contains(".")) return;
        if (currentOperand.equals("0") && !number.equals(".")) currentOperand = "";
        currentOperand += number;
    }

    public void chooseOperation(String op) {
       
        if (!currentOperand.isEmpty()) {
            currentOperand += " " + op + " ";
        }
    }

    // ------------------ Compute Expression ------------------
    public void compute() {
        if (currentOperand.isEmpty()) return;

        try {
          
            Operation op = ExpressionParser.parse(currentOperand);
            float result = op.execute();
            currentOperand = (result - (int) result) != 0
                    ? Float.toString(result)
                    : Integer.toString((int) result);

            previousOperand = "";
            operation = "";

        } catch (Exception e) {
            currentOperand = "Error";
        }
    }

    // ------------------ Utility Methods ------------------
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
            try {
                float tmp = -Float.parseFloat(currentOperand);
                currentOperand = (tmp - (int) tmp) != 0 ? Float.toString(tmp) : Integer.toString((int) tmp);
            } catch (NumberFormatException e) {
               
            }
        }
    }
}

