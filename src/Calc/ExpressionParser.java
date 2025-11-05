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


import java.util.*;
import java.util.regex.*;

public class ExpressionParser {

    private static final Map<String, Integer> PRECEDENCE = new HashMap<>();
    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("×", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("÷", 2);
        PRECEDENCE.put("mod", 2);
        PRECEDENCE.put("%", 2);
        PRECEDENCE.put("power", 3);
        PRECEDENCE.put("^", 3);
        PRECEDENCE.put("root", 3);
        PRECEDENCE.put("log", 3);
    }

    public static Operation parse(String expression) {
        if (expression == null || expression.trim().isEmpty())
            throw new IllegalArgumentException("Empty expression");

        String expr = expression.replaceAll("\\s+", "");

     
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("([0-9]*\\.?[0-9]+|power|root|log|mod|[()+\\-×÷*/%^])").matcher(expr);
        while (m.find()) tokens.add(m.group());

        Stack<Operation> values = new Stack<>();
        Stack<String> ops = new Stack<>();

        for (int i = 0; i < tokens.size(); i++) {
            String tok = tokens.get(i);

            if (tok.matches("[0-9]*\\.?[0-9]+")) {
                values.push(new Value(Float.parseFloat(tok)));
                continue;
            }

            if (tok.equals("(")) {
                ops.push(tok);
                continue;
            }

            if (tok.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    applyTopOperation(values, ops.pop());
                }
                if (!ops.isEmpty() && ops.peek().equals("(")) ops.pop();
                continue;
            }

            // operator
            if (isOperator(tok)) {
                while (!ops.isEmpty() && !ops.peek().equals("(") &&
                       precedence(ops.peek()) >= precedence(tok)) {
                    applyTopOperation(values, ops.pop());
                }
                ops.push(tok);
                continue;
            }

            throw new IllegalArgumentException("Invalid token: " + tok);
        }

        while (!ops.isEmpty()) {
            applyTopOperation(values, ops.pop());
        }

        if (values.isEmpty()) throw new IllegalArgumentException("Invalid expression");
        return values.pop();
    }

    private static boolean isOperator(String s) {
        return PRECEDENCE.containsKey(s);
    }

    private static int precedence(String s) {
        return PRECEDENCE.getOrDefault(s, 0);
    }

    private static void applyTopOperation(Stack<Operation> values, String op) {
        if (values.size() < 2) throw new IllegalArgumentException("Insufficient values");
        Operation right = values.pop();
        Operation left = values.pop();
        Operation combined = OperationFactory.createBinaryOperation(op, left, right);
        values.push(combined);
    }
}
