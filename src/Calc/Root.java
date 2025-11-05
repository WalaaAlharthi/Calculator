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


public class Root implements Operation {
    private final Operation left, right;
    public Root(Operation left, Operation right) {
        this.left = left; this.right = right;
    }
    @Override
    public float execute() {
        return (float) Math.pow(left.execute(), 1.0 / right.execute());
    }
}

