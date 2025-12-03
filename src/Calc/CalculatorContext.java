/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class CalculatorContext {

    private CalculatorState state;
    private CalculatorState previousState;
    private final CalculatorFacade facade;

    // الفلاغ الجديد اللي يحل المشكلة
    private boolean justComputed = false;

    public CalculatorContext(CalculatorFacade facade) {
        this.facade = facade;
        this.state = new InputState();   // الحالة الابتدائية
        this.previousState = null;
    }

    public CalculatorState getState() {
        return state;
    }

    public CalculatorState getPreviousState() {
        return previousState;
    }

    public void setState(CalculatorState newState) {
        this.previousState = this.state;
        this.state = newState;
    }

    // -------------------------
    //  JUST COMPUTED FLAG
    // -------------------------

    public boolean didJustCompute() {
        return justComputed;
    }

    public void setJustComputed(boolean value) {
        this.justComputed = value;
    }

    public CalculatorFacade getFacade() {
        return facade;
    }
}
