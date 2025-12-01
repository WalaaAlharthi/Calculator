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


public class Multiplication implements Operation {
    private final Operation left, right;
    public Multiplication(Operation left, Operation right) {
        this.left = left; this.right = right;
    }
    @Override
    public float evaluate() {
        return left.evaluate() * right.evaluate();
    }
}

