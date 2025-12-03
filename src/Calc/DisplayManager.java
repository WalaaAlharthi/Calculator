/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class DisplayManager {

    public String formatCurrent(String value) {

        // لو القيمة null أو Error → رجّعها كما هي
        if (value == null || value.equals("Error")) {
            return value;
        }

        // أهم تعديل: إذا رجعت القيمة من UNDO يعني user رجع خطوة
        // لا نعرض 0، نعرضها كما هي
        if (value.isEmpty()) {
            return "";
        }

        return value;
    }

    public String formatPrevious(String prev, String op) {

        if (prev == null || op == null) return "";

        if (prev.isEmpty() || op.isEmpty()) {
            return "";
        }

        return prev + " " + op;
    }
}
