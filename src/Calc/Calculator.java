package Calc;

import Calc.commands.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public final class Calculator extends JFrame {

    // ---------------- Singleton ----------------
    private static Calculator instance;
    public static Calculator getInstance() {
        if (instance == null) instance = new Calculator();
        return instance;
    }

    // ---------------- Fields ----------------
    private final CalculatorFacade facade = new CalculatorFacade();
    private final CommandInvoker invoker = new CommandInvoker();

    private JTextField displayCurrent;
    private JTextField displayPrevious;

    // Buttons (kept as fields if you want later access)
    private JButton btnDel, btnClear, btnUndo, btnRedo;
    private JButton btn7, btn8, btn9, btnDiv;
    private JButton btn4, btn5, btn6, btnMult;
    private JButton btn1, btn2, btn3, btnEquals;
    private JButton btnPlusMinus, btn0, btnDot, btnPlus;
    private JButton btnPow, btnRoot, btnLog, btnMod;

    // ---------------- Constructor (private for singleton) ----------------
    private Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 560);
        setResizable(false);
        setLocationRelativeTo(null);

        // Use BorderLayout: top display, center buttons
        setLayout(new BorderLayout());
        initDisplay();
        initButtons();
        updateDisplay();

        // make visible
        setVisible(true);
    }

    // ---------------- Create styled button helper ----------------
    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setFont(new Font("Century Gothic", Font.BOLD, 18));
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(34, 34, 34));
        b.setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34)));
        return b;
    }

    // ---------------- Display (dark) ----------------
    private void initDisplay() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(2, 1));
        displayPanel.setBackground(new Color(13, 12, 20));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        displayPrevious = new JTextField();
        displayPrevious.setEditable(false);
        displayPrevious.setBackground(new Color(21, 20, 22));
        displayPrevious.setForeground(new Color(203, 198, 213));
        displayPrevious.setFont(new Font("Century Gothic", Font.BOLD, 16));
        displayPrevious.setHorizontalAlignment(JTextField.RIGHT);
        displayPrevious.setBorder(null);

        displayCurrent = new JTextField();
        displayCurrent.setEditable(false);
        displayCurrent.setBackground(new Color(41, 39, 44));
        displayCurrent.setForeground(Color.WHITE);
        displayCurrent.setFont(new Font("Century Gothic", Font.BOLD, 28));
        displayCurrent.setHorizontalAlignment(JTextField.RIGHT);
        displayCurrent.setBorder(null);

        displayPanel.add(displayPrevious);
        displayPanel.add(displayCurrent);

        add(displayPanel, BorderLayout.NORTH);
    }

    // ---------------- Buttons grid and listeners ----------------
    private void initButtons() {
        JPanel grid = new JPanel(new GridLayout(6, 4, 6, 6));
        grid.setBackground(new Color(21, 20, 22));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Row 1: DEL, C, Undo, Redo
        btnDel = createButton("←");
        btnClear = createButton("C");
        btnUndo = createButton("Undo");
        btnRedo = createButton("Redo");

        // Row 2
        btn7 = createButton("7");
        btn8 = createButton("8");
        btn9 = createButton("9");
        btnDiv = createButton("÷");

        // Row 3
        btn4 = createButton("4");
        btn5 = createButton("5");
        btn6 = createButton("6");
        btnMult = createButton("×");

        // Row 4
        btn1 = createButton("1");
        btn2 = createButton("2");
        btn3 = createButton("3");
        btnEquals = createButton("=");

        // Row 5
        btnPlusMinus = createButton("+/-");
        btn0 = createButton("0");
        btnDot = createButton(".");
        btnPlus = createButton("+");

        // Row 6
        btnPow = createButton("^");
        btnRoot = createButton("√");
        btnLog = createButton("log");
        btnMod = createButton("%");

        // Add all in order
        grid.add(btnDel);     grid.add(btnClear); grid.add(btnUndo);  grid.add(btnRedo);
        grid.add(btn7);       grid.add(btn8);      grid.add(btn9);      grid.add(btnDiv);
        grid.add(btn4);       grid.add(btn5);      grid.add(btn6);      grid.add(btnMult);
        grid.add(btn1);       grid.add(btn2);      grid.add(btn3);      grid.add(btnEquals);
        grid.add(btnPlusMinus); grid.add(btn0);    grid.add(btnDot);    grid.add(btnPlus);
        grid.add(btnPow);     grid.add(btnRoot);   grid.add(btnLog);    grid.add(btnMod);

        add(grid, BorderLayout.CENTER);

        // ----------------- Attach listeners (Command pattern) -----------------

        // Numbers and dot
        ActionListener numberListener = e -> {
            String txt = ((JButton) e.getSource()).getText();
            // map "+/-" to the toggle sign command
            if (txt.equals("+/-")) {
                invoker.execute(new ToggleSignCommand(facade));
            } else {
                invoker.execute(new AppendNumberCommand(facade, txt));
            }
            updateDisplay();
        };

        btn0.addActionListener(numberListener);
        btn1.addActionListener(numberListener);
        btn2.addActionListener(numberListener);
        btn3.addActionListener(numberListener);
        btn4.addActionListener(numberListener);
        btn5.addActionListener(numberListener);
        btn6.addActionListener(numberListener);
        btn7.addActionListener(numberListener);
        btn8.addActionListener(numberListener);
        btn9.addActionListener(numberListener);
        btnDot.addActionListener(numberListener);
        btnPlusMinus.addActionListener(numberListener);

        // Operators
        btnDiv.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "÷")); updateDisplay(); });
        btnMult.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "×")); updateDisplay(); });
        btnPlus.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "+")); updateDisplay(); });
        btnEquals.addActionListener(e -> {
            try {
                invoker.execute(new ComputeCommand(facade));
            } catch (Exception ex) {
                facade.showError();
            }
            updateDisplay();
        });

        // Side operators
        btnPow.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "^")); updateDisplay(); });
        btnRoot.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "root")); updateDisplay(); });
        btnLog.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "log")); updateDisplay(); });
        btnMod.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(facade, "%")); updateDisplay(); });

        // Clear, Delete, Undo, Redo
        btnClear.addActionListener(e -> { invoker.execute(new ClearCommand(facade)); updateDisplay(); });
        btnDel.addActionListener(e -> { invoker.execute(new DeleteCommand(facade)); updateDisplay(); });
        btnUndo.addActionListener(e -> { invoker.undo(); updateDisplay(); });
        btnRedo.addActionListener(e -> { invoker.redo(); updateDisplay(); });
    }

    // ---------------- Update display from facade ----------------
    private void updateDisplay() {
        displayCurrent.setText(facade.getCurrentDisplay());
        displayPrevious.setText(facade.getPreviousDisplay());
    }
}
