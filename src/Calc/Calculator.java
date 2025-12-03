package Calc;

import Calc.commands.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public final class Calculator extends JFrame {

    // ---------------- Singleton ----------------
    private static Calculator instance;
    public static Calculator getInstance() {
        if (instance == null) instance = new Calculator();
        return instance;
    }

    // ---------------- Core Objects ----------------
    private final CalculatorFacade facade = new CalculatorFacade();
    private final CalculatorContext ctx = new CalculatorContext(facade);
    private final CommandInvoker invoker = new CommandInvoker();

    // ---------------- UI Components ----------------
    private JTextField displayCurrent;
    private JTextField displayPrevious;

    // Normal operators
    private JButton btnDel, btnClear, btnUndo, btnRedo;
    private JButton btn7, btn8, btn9, btnDiv;
    private JButton btn4, btn5, btn6, btnMult;
    private JButton btn1, btn2, btn3, btnEquals;
    private JButton btnPlusMinus, btn0, btnDot, btnPlus;
    private JButton btnPow, btnRoot, btnLog, btnMod;

    // Memory buttons
    private JButton btnMPlus, btnMMinus, btnMR, btnMC;

    // History button
    private JButton btnHistory;

    // ---------------- Constructor ----------------
    private Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 620);   // زودنا 60 عشان صف جديد من الأزرار
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        initDisplay();
        initButtons();
        updateDisplay();

        setVisible(true);
    }

    // ---------------- Button Styling ----------------
    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setFont(new Font("Century Gothic", Font.BOLD, 18));
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(34, 34, 34));
        b.setBorder(BorderFactory.createLineBorder(new Color(34, 34, 34)));
        return b;
    }

    // ---------------- Display Setup ----------------
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

    // ---------------- Buttons + Commands ----------------
    private void initButtons() {

        JPanel grid = new JPanel(new GridLayout(7, 4, 6, 6)); // 7 صفوف بدل 6
        grid.setBackground(new Color(21, 20, 22));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---------------- Row 1: Memory Buttons ----------------
        btnMPlus = createButton("M+");
        btnMMinus = createButton("M-");
        btnMR = createButton("MR");
        btnMC = createButton("MC");

        grid.add(btnMPlus);
        grid.add(btnMMinus);
        grid.add(btnMR);
        grid.add(btnMC);

        // ---------------- Row 2: DEL, C, Undo, Redo ----------------
        btnDel = createButton("←");
        btnClear = createButton("C");
        btnUndo = createButton("Undo");
        btnRedo = createButton("Redo");

        grid.add(btnDel); 
        grid.add(btnClear); 
        grid.add(btnUndo);  
        grid.add(btnRedo);

        // ---------------- Row 3 ----------------
        btn7 = createButton("7");
        btn8 = createButton("8");
        btn9 = createButton("9");
        btnDiv = createButton("÷");

        grid.add(btn7); grid.add(btn8); grid.add(btn9); grid.add(btnDiv);

        // ---------------- Row 4 ----------------
        btn4 = createButton("4");
        btn5 = createButton("5");
        btn6 = createButton("6");
        btnMult = createButton("×");

        grid.add(btn4); grid.add(btn5); grid.add(btn6); grid.add(btnMult);

        // ---------------- Row 5 ----------------
        btn1 = createButton("1");
        btn2 = createButton("2");
        btn3 = createButton("3");
        btnEquals = createButton("=");

        grid.add(btn1); grid.add(btn2); grid.add(btn3); grid.add(btnEquals);

        // ---------------- Row 6 ----------------
        btnPlusMinus = createButton("+/-");
        btn0 = createButton("0");
        btnDot = createButton(".");
        btnPlus = createButton("+");

        grid.add(btnPlusMinus); grid.add(btn0); grid.add(btnDot); grid.add(btnPlus);

        // ---------------- Row 7 ----------------
        btnPow = createButton("^");
        btnRoot = createButton("√");
        btnLog = createButton("log");
        btnMod = createButton("%");

        grid.add(btnPow); grid.add(btnRoot); grid.add(btnLog); grid.add(btnMod);

        // ---------------- Add grid ----------------
        add(grid, BorderLayout.CENTER);

        // ---------------- EVENT HANDLERS ----------------

        // Numbers
        ActionListener numberListener = e -> {
            String txt = ((JButton)e.getSource()).getText();
            if (txt.equals("+/-")) {
                invoker.execute(new ToggleSignCommand(ctx));
            } else {
                invoker.execute(new AppendNumberCommand(ctx, txt));
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
        btnDiv.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "÷")); updateDisplay(); });
        btnMult.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "×")); updateDisplay(); });
        btnPlus.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "+")); updateDisplay(); });

        btnEquals.addActionListener(e -> {
            try {
                invoker.execute(new ComputeCommand(ctx));
            } catch (Exception ex) {
                facade.showError();
            }
            updateDisplay();
        });

        btnPow.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "^")); updateDisplay(); });
        btnRoot.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "root")); updateDisplay(); });
        btnLog.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "log")); updateDisplay(); });
        btnMod.addActionListener(e -> { invoker.execute(new ChooseOperationCommand(ctx, "%")); updateDisplay(); });

        // Clear / Delete / Undo / Redo
        btnClear.addActionListener(e -> { invoker.execute(new ClearCommand(ctx)); updateDisplay(); });
        btnDel.addActionListener(e -> { invoker.execute(new DeleteCommand(ctx)); updateDisplay(); });
        btnUndo.addActionListener(e -> { invoker.undo(); updateDisplay(); });
        btnRedo.addActionListener(e -> { invoker.redo(); updateDisplay(); });

        // ---------------- Memory Buttons Events ----------------
        btnMPlus.addActionListener(e -> { facade.memoryAdd(); });
        btnMMinus.addActionListener(e -> { facade.memorySubtract(); });
        btnMR.addActionListener(e -> { facade.memoryRecall(); updateDisplay(); });
        btnMC.addActionListener(e -> { facade.memoryClear(); });

        // ---------------- History Button ----------------
        btnHistory = createButton("History");
        add(btnHistory, BorderLayout.SOUTH);

        btnHistory.addActionListener(e -> openHistoryWindow());
    }

    // ---------------- History Popup ----------------
    private void openHistoryWindow() {
    java.util.List<String> hist = facade.getHistory();

    JTextArea area = new JTextArea(15, 25);
    area.setEditable(false);

    if (hist.isEmpty()) {
        area.setText("No history yet.");
    } else {
        StringBuilder sb = new StringBuilder();
        for (String h : hist) {
            sb.append(h).append("\n");
        }
        area.setText(sb.toString());
    }

    JScrollPane pane = new JScrollPane(area);
    JOptionPane.showMessageDialog(this, pane, "History", JOptionPane.PLAIN_MESSAGE);
}

    // ---------------- Update Display ----------------
    private void updateDisplay() {
        displayCurrent.setText(facade.getCurrentDisplay());
        displayPrevious.setText(facade.getPreviousDisplay());
    }
}

