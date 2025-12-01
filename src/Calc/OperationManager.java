/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class OperationManager {

    public String compute(StateManager state) {

        // Build full expression: "previous operator current"
        String expression = state.getPrevious()
                + state.getOperator()
                + state.getCurrent();

        // Parse into expression tree
        Operation op = ExpressionParser.parse(expression);

        // Evaluate using evaluate()
        float result = op.evaluate();

        // return as string
        return String.valueOf(result);
    }
}

