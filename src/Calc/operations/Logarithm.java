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

public class Logarithm implements Operation {
    private final Operation left, right;
    public Logarithm(Operation left, Operation right) {
        this.left = left; this.right = right;
    }
    @Override
    public float evaluate() {
        return (float) (Math.log(right.evaluate()) / Math.log(left.evaluate()));
    }
}
