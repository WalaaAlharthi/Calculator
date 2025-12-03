package Calc;

public class OperationSelectedState implements CalculatorState {

    @Override
    public void onNumber(CalculatorContext ctx, String digit) {
        ctx.getFacade().forceSetCurrent("");
        ctx.getFacade().appendNumber(digit);
        ctx.setState(new InputState());
    }

    @Override
    public void onOperation(CalculatorContext ctx, String op) {

        // هذا يعيد سلوكك القديم ويصلّح العمليات المركبة:
        // previous + operator + current → compute

        if (!ctx.getFacade().getRawPrevious().isEmpty()
         && !ctx.getFacade().getRawCurrent().isEmpty()
         && !ctx.getFacade().getRawOperator().isEmpty()) {

            // احسبي العملية المركبة
            ctx.getFacade().compute();
        }

        // الآن ابدأ العملية التالية على الناتج
        ctx.getFacade().setPreviousRaw(ctx.getFacade().getRawCurrent());
        ctx.getFacade().setOperatorRaw(op);
        ctx.getFacade().forceSetCurrent("");
    }

    @Override
    public void onEquals(CalculatorContext ctx) {
        ctx.getFacade().compute();
        ctx.setState(new ResultState());
    }

    @Override
    public void onClear(CalculatorContext ctx) {
        ctx.getFacade().clear();
        ctx.setState(new InputState());
    }

    @Override public void onDelete(CalculatorContext ctx) {}
    @Override public void onToggleSign(CalculatorContext ctx) {}
}
