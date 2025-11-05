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



public class Modulus implements Operation {
    private Operation left, right;

    public Modulus(Operation left, Operation right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public float execute() {
        float l = left.execute();
        float r = right.execute();
        if (r == 0) throw new ArithmeticException("Division by zero in modulus");
        return l % r;
    }
}
