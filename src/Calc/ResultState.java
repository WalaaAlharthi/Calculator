/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

public class ResultState implements CalculatorState {

    @Override
    public void onNumber(CalculatorContext ctx, String digit) {

        // بدء إدخال جديد بعد النتيجة
        ctx.getFacade().forceSetCurrent("");
        ctx.getFacade().setPreviousRaw("");
        ctx.getFacade().setOperatorRaw("");

        ctx.getFacade().appendNumber(digit);

        // نرجع لـ InputState
        ctx.setState(new InputState());
    }

    @Override
    public void onOperation(CalculatorContext ctx, String op) {

        // عند الضغط على عملية بعد النتيجة:
        // نبدأ عملية جديدة من current الحالي (النتيجة)
        ctx.getFacade().setPreviousRaw(ctx.getFacade().getRawCurrent());
        ctx.getFacade().setOperatorRaw(op);
        ctx.getFacade().forceSetCurrent("");

        ctx.setState(new OperationSelectedState());
    }

    @Override
    public void onEquals(CalculatorContext ctx) {
        // يعيد نفس النتيجة - behavior طبيعي
    }

    @Override
    public void onClear(CalculatorContext ctx) {
        ctx.getFacade().clear();
        ctx.setState(new InputState());
    }

    @Override
    public void onDelete(CalculatorContext ctx) {
        // لا شيء
    }

    @Override
    public void onToggleSign(CalculatorContext ctx) {
        ctx.getFacade().toggleSign();
    }
}
