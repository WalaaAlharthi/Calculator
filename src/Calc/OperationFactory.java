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

    public static Operation createBinaryOperation(String op, Operation left, Operation right) {
        switch (op) {
            case "+":
                return new Addition(left, right);
            case "-":
                return new Subtraction(left, right);
            case "*":
                return new Multiplication(left, right);
            case "/":
                return new Division(left, right);
            case "^":
            case "power":
                return new Power(left, right);
            case "root":
                return new Root(left, right);
            case "log":
                return new Logarithm(left, right);
            case "%": 
                return new Modulus(left, right);
            default:
                throw new IllegalArgumentException("Unsupported operation: " + op);
        }
    }
}
