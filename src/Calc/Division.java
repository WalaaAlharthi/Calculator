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

public class Division implements Operation {
    private final Operation left, right;
    public Division(Operation left, Operation right) {
        this.left = left; this.right = right;
    }
    @Override
    public float evaluate() {
        float r = right.evaluate();
        if (r == 0) throw new ArithmeticException("Division by zero");
        return left.evaluate() / r;
    }
}

