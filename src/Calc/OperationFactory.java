/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

/**
 *
 * @author reham
 */
// File: OperationFactory.java


public class OperationFactory {

    public static Operation getOperation(String op) {
        if (op.equals("+")) {
            return new Addition();
        } else if (op.equals("-")) {
            return new Subtraction();
        } else if (op.equals("×")) {
            return new Multiplication();
        } else if (op.equals("÷")) {
            return new Division();
        } else {
            return null;
        }
    }
}
