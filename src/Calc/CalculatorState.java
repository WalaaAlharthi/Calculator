/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

/**
 *
 * @author wla03
 */
public interface CalculatorState {
    void onNumber(CalculatorContext ctx, String digit);
    void onOperation(CalculatorContext ctx, String op);
    void onEquals(CalculatorContext ctx);
    void onClear(CalculatorContext ctx);
    void onDelete(CalculatorContext ctx);
    void onToggleSign(CalculatorContext ctx);
}
