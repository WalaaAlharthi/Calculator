package Calc;

public class InputState implements CalculatorState {

    @Override
    public void onNumber(CalculatorContext ctx, String digit) {

        // إذا كنا جايين من نتيجة → نبدأ كتابة جديدة
       if (ctx.didJustCompute()) {
    ctx.getFacade().forceSetCurrent("");   // يمنع اللصق
    ctx.getFacade().appendNumber(digit);
    ctx.setJustComputed(false);            // انتهينا من حالة بعد النتيجة
    ctx.setState(new InputState());
    return;


        }

        // في الحالات العادية
        ctx.getFacade().appendNumber(digit);
    }


    @Override
    public void onOperation(CalculatorContext ctx, String op) {
        ctx.getFacade().chooseOperation(op);
        ctx.setState(new OperationSelectedState());
    }

    @Override
    public void onEquals(CalculatorContext ctx) {
        try {
            ctx.getFacade().compute();
            ctx.setJustComputed(true);
            ctx.setState(new ResultState());
        } catch (Exception ex) {
            ctx.getFacade().showError();
        }
    }

    @Override
    public void onClear(CalculatorContext ctx) {
        ctx.getFacade().clear();
        ctx.setState(new InputState());
    }

    @Override
    public void onDelete(CalculatorContext ctx) {
        ctx.getFacade().deleteLast();
    }

    @Override
    public void onToggleSign(CalculatorContext ctx) {
        ctx.getFacade().toggleSign();
    }
}
