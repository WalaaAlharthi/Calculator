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
import java.util.ArrayList;
import java.util.List;



public class CompositeOperation implements Operation {
    private Operation root;

    public CompositeOperation() { }

    public CompositeOperation(Operation root) {
        this.root = root;
    }

    public void setRoot(Operation root) {
        this.root = root;
    }

    @Override
    public float evaluate() {
        if (root == null) throw new IllegalStateException("No operation set");
        return root.evaluate();
    }
}

