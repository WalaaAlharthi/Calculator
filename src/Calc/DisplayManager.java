/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class DisplayManager {

    public String formatCurrent(String value) {
        return value.isEmpty() ? "0" : value;
    }

    public String formatPrevious(String prev, String op) {
        if (prev.isEmpty() || op.isEmpty()) return "";
        return prev + " " + op;
    }
}
