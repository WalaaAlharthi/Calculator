/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class InputManager {

    public String appendNumber(String current, String num) {
        if (num.equals(".") && current.contains(".")) return current;

        if (current.equals("0") && !num.equals(".")) {
            current = "";
        }

        return current + num;
    }

    public String deleteLast(String current) {
        if (current == null || current.isEmpty()) return "";
        return current.substring(0, current.length() - 1);
    }

    public String toggleSign(String current) {
        if (current == null || current.isEmpty()) return "";

        try {
            float v = Float.parseFloat(current);
            v = -v;
            return (v == (int)v) ? Integer.toString((int)v) : Float.toString(v);
        } catch (Exception e) {
            return current;
        }
    }

    public String buildExpression(String previous, String operator, String current) {
        return previous + operator + current;
    }
}
