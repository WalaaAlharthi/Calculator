/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class Value implements Operation {
    private final float value;

    public Value(float value) {
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }
}
