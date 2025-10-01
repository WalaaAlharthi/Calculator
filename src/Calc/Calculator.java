// File: Calculator.java
package Calc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class Calculator extends JFrame {

    // ------------------ Singleton ------------------
    private static Calculator instance = null;
    private Calculator() { initComponents(); addEvents(); clear(); }
    public static Calculator getInstance() {
        if (instance == null){ 
            instance = new Calculator();
        }
        return instance;
    }

    // ------------------ State ------------------
    private String currentOperand = "";
    private String previousOperand = "";
    private String operation = "";
    private int x, y;

    // ------------------ UI ------------------
    private JPanel app, buttonsPanel, resultsPanel, titleBar;
    private JTextField current, previous;
    private JLabel title;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnPlus, btnSub, btnMult, btnDiv, btnEqual, btnClear, btnDel, btnDot, btnPlusSub;

    // ------------------ Factory Method for Buttons ------------------
    private JButton createButton(String text, Color bgColor, Color fgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Century Gothic", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(bgColor));
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    // ------------------ Initialize UI ------------------
    private void initComponents() {
        app = new JPanel();
        resultsPanel = new JPanel();
        buttonsPanel = new JPanel();
        titleBar = new JPanel();
        title = new JLabel("Calculator");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocation(new Point(500, 100));
        setUndecorated(true);
        setResizable(false);

        app.setBackground(new Color(13, 12, 20));
        app.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        resultsPanel.setBackground(new Color(34, 34, 34));
        resultsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        previous = new JTextField();
        previous.setEditable(false);
        previous.setBackground(new Color(21, 20, 22));
        previous.setFont(new Font("Century Gothic", Font.BOLD, 18));
        previous.setForeground(new Color(203, 198, 213));
        previous.setHorizontalAlignment(JTextField.RIGHT);
        previous.setBorder(null);
        resultsPanel.add(previous, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 50));

        current = new JTextField();
        current.setEditable(false);
        current.setBackground(new Color(41, 39, 44));
        current.setFont(new Font("Century Gothic", Font.BOLD, 24));
        current.setForeground(Color.WHITE);
        current.setHorizontalAlignment(JTextField.RIGHT);
        current.setBorder(null);
        resultsPanel.add(current, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 60));

        app.add(resultsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, 110));

        buttonsPanel.setBackground(new Color(21, 20, 22));
        buttonsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Create buttons
        btn0 = createButton("0", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn1 = createButton("1", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn2 = createButton("2", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn3 = createButton("3", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn4 = createButton("4", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn5 = createButton("5", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn6 = createButton("6", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn7 = createButton("7", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn8 = createButton("8", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btn9 = createButton("9", new Color(21, 20, 22), Color.WHITE, 70, 70);

        btnPlus = createButton("+", new Color(41, 39, 44), Color.WHITE, 70, 140);
        btnSub = createButton("-", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnMult = createButton("×", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnDiv = createButton("÷", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnEqual = createButton("=", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnClear = createButton("C", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnDel = createButton("←", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnDot = createButton(".", new Color(21, 20, 22), Color.WHITE, 70, 70);
        btnPlusSub = createButton("+/-", new Color(21, 20, 22), Color.WHITE, 70, 70);

        // Add buttons to panel (layout same as original)
        buttonsPanel.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        buttonsPanel.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));
        buttonsPanel.add(btnDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));
        buttonsPanel.add(btnMult, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        buttonsPanel.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        buttonsPanel.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));
        buttonsPanel.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));
        buttonsPanel.add(btnSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        buttonsPanel.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        buttonsPanel.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));
        buttonsPanel.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));
        buttonsPanel.add(btnPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, 140));

        buttonsPanel.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));
        buttonsPanel.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));
        buttonsPanel.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));
        buttonsPanel.add(btnEqual, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        buttonsPanel.add(btnPlusSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));
        buttonsPanel.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));
        buttonsPanel.add(btnDot, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        app.add(buttonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 320, 390));

        titleBar.setBackground(new Color(21, 20, 22));
        titleBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        title.setFont(new Font("Century Gothic", Font.BOLD, 17));
        title.setForeground(Color.WHITE);
        titleBar.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 2, -1, 25));
        app.add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 30));

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(app, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 530));
        pack();
    }

    // ------------------ Events ------------------
    private void addEvents() {
        JButton[] numbers = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        JButton[] operations = {btnPlus, btnSub, btnMult, btnDiv};

        for (JButton number : numbers) number.addActionListener(e -> appendNumber(number.getText()));
        for (JButton op : operations) op.addActionListener(e -> chooseOperation(op.getText()));

        btnDot.addActionListener(e -> appendNumber(currentOperand.isEmpty() ? "0." : "."));
        btnEqual.addActionListener(e -> { compute(); updateDisplay(); if(currentOperand.equals("Error")) currentOperand=""; });
        btnClear.addActionListener(e -> clear());
        btnDel.addActionListener(e -> { if(!currentOperand.isEmpty()) { currentOperand=currentOperand.substring(0,currentOperand.length()-1); updateDisplay(); }});
        btnPlusSub.addActionListener(e -> { if(!currentOperand.isEmpty()){ float tmp=-Float.parseFloat(currentOperand); currentOperand=(tmp-(int)tmp)!=0?Float.toString(tmp):Integer.toString((int)tmp); updateDisplay(); }});

        titleBar.addMouseListener(new MouseAdapter(){ public void mousePressed(MouseEvent evt){ x=evt.getX(); y=evt.getY(); }});
        titleBar.addMouseMotionListener(new MouseMotionAdapter(){ public void mouseDragged(MouseEvent evt){ setLocation(evt.getXOnScreen()-x, evt.getYOnScreen()-y); }});
    }

    // ------------------ Calculator Logic ------------------
    private void appendNumber(String number){ if(number.equals(".") && currentOperand.contains(".")) return; if(currentOperand.equals("0") && !number.equals(".")) currentOperand=""; currentOperand+=number; updateDisplay(); }
    private void chooseOperation(String op){ if(!currentOperand.isEmpty()){ if(!previousOperand.isEmpty()) compute(); operation=op; previousOperand=currentOperand; currentOperand=""; updateDisplay(); }}
    private void compute(){
        if(currentOperand.isEmpty() || previousOperand.isEmpty()) return;
        float curr=Float.parseFloat(currentOperand);
        float prev=Float.parseFloat(previousOperand);
        Operation operationObj=OperationFactory.getOperation(operation);
        if(operationObj==null) return;
        try{ float result=operationObj.execute(prev,curr); currentOperand=(result-(int)result)!=0?Float.toString(result):Integer.toString((int)result); }catch(ArithmeticException e){ currentOperand="Error"; }
        previousOperand=""; operation="";
    }
    private void clear(){ currentOperand=""; previousOperand=""; operation=""; updateDisplay(); }
    private void updateDisplay(){ current.setText(currentOperand); previous.setText(previousOperand+" "+operation); }
}

/*
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        app = new javax.swing.JPanel();
        resultsPanel = new javax.swing.JPanel();
        previous = new javax.swing.JTextField();
        current = new javax.swing.JTextField();
        buttonsPanel = new javax.swing.JPanel();
        btnDel = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDiv = new javax.swing.JButton();
        btnMult = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnSub = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnPlusSub = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnEqual = new javax.swing.JButton();
        titleBar = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        btnMini = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocation(new java.awt.Point(500, 100));
        setUndecorated(true);
        setResizable(false);

        app.setBackground(new java.awt.Color(13, 12, 20));
        app.setForeground(new java.awt.Color(40, 40, 40));
        app.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        resultsPanel.setBackground(new java.awt.Color(34, 34, 34));
        resultsPanel.setForeground(new java.awt.Color(57, 57, 57));
        resultsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        previous.setEditable(false);
        previous.setBackground(new java.awt.Color(21, 20, 22));
        previous.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        previous.setForeground(new java.awt.Color(203, 198, 213));
        previous.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        previous.setBorder(null);
        resultsPanel.add(previous, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 50));

        current.setEditable(false);
        current.setBackground(new java.awt.Color(41, 39, 44));
        current.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        current.setForeground(new java.awt.Color(255, 255, 255));
        current.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        current.setBorder(null);
        resultsPanel.add(current, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 60));

        app.add(resultsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 320, 110));

        buttonsPanel.setBackground(new java.awt.Color(21, 20, 22));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(250, 50));
        buttonsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDel.setBackground(new java.awt.Color(41, 39, 44));
        btnDel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setText("←");
        btnDel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnDel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDel.setFocusPainted(false);
        btnDel.setIconTextGap(1);
        btnDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDel.setPreferredSize(new java.awt.Dimension(70, 70));
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnClear.setBackground(new java.awt.Color(41, 39, 44));
        btnClear.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("C");
        btnClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.setFocusPainted(false);
        btnClear.setIconTextGap(1);
        btnClear.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnClear.setPreferredSize(new java.awt.Dimension(70, 70));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        btnDiv.setBackground(new java.awt.Color(41, 39, 44));
        btnDiv.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDiv.setForeground(new java.awt.Color(255, 255, 255));
        btnDiv.setText("÷");
        btnDiv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnDiv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDiv.setFocusPainted(false);
        btnDiv.setIconTextGap(1);
        btnDiv.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDiv.setPreferredSize(new java.awt.Dimension(70, 70));
        btnDiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnDiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        btnMult.setBackground(new java.awt.Color(41, 39, 44));
        btnMult.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnMult.setForeground(new java.awt.Color(255, 255, 255));
        btnMult.setText("×");
        btnMult.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnMult.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMult.setFocusPainted(false);
        btnMult.setIconTextGap(1);
        btnMult.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnMult.setPreferredSize(new java.awt.Dimension(70, 70));
        btnMult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnMult, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        btn7.setBackground(new java.awt.Color(21, 20, 22));
        btn7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn7.setForeground(new java.awt.Color(255, 255, 255));
        btn7.setText("7");
        btn7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn7.setFocusPainted(false);
        btn7.setIconTextGap(1);
        btn7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn7.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        btn8.setBackground(new java.awt.Color(21, 20, 22));
        btn8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn8.setForeground(new java.awt.Color(255, 255, 255));
        btn8.setText("8");
        btn8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn8.setFocusPainted(false);
        btn8.setIconTextGap(1);
        btn8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn8.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        btn9.setBackground(new java.awt.Color(21, 20, 22));
        btn9.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn9.setForeground(new java.awt.Color(255, 255, 255));
        btn9.setText("9");
        btn9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn9.setFocusPainted(false);
        btn9.setIconTextGap(1);
        btn9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn9.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        btnSub.setBackground(new java.awt.Color(41, 39, 44));
        btnSub.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSub.setForeground(new java.awt.Color(255, 255, 255));
        btnSub.setText("-");
        btnSub.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnSub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSub.setFocusPainted(false);
        btnSub.setIconTextGap(1);
        btnSub.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSub.setPreferredSize(new java.awt.Dimension(70, 70));
        btnSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        btn4.setBackground(new java.awt.Color(21, 20, 22));
        btn4.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn4.setForeground(new java.awt.Color(255, 255, 255));
        btn4.setText("4");
        btn4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn4.setFocusPainted(false);
        btn4.setIconTextGap(1);
        btn4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn4.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        btn5.setBackground(new java.awt.Color(21, 20, 22));
        btn5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn5.setForeground(new java.awt.Color(255, 255, 255));
        btn5.setText("5");
        btn5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn5.setFocusPainted(false);
        btn5.setIconTextGap(1);
        btn5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn5.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        btn6.setBackground(new java.awt.Color(21, 20, 22));
        btn6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn6.setForeground(new java.awt.Color(255, 255, 255));
        btn6.setText("6");
        btn6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn6.setFocusPainted(false);
        btn6.setIconTextGap(1);
        btn6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn6.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        btnPlus.setBackground(new java.awt.Color(41, 39, 44));
        btnPlus.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnPlus.setText("+");
        btnPlus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnPlus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlus.setFocusPainted(false);
        btnPlus.setIconTextGap(1);
        btnPlus.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPlus.setPreferredSize(new java.awt.Dimension(70, 140));
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        btn1.setBackground(new java.awt.Color(21, 20, 22));
        btn1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("1");
        btn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.setFocusPainted(false);
        btn1.setIconTextGap(1);
        btn1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn1.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        btn2.setBackground(new java.awt.Color(21, 20, 22));
        btn2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("2");
        btn2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.setFocusPainted(false);
        btn2.setIconTextGap(1);
        btn2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn2.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, -1));

        btn3.setBackground(new java.awt.Color(21, 20, 22));
        btn3.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setText("3");
        btn3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.setFocusPainted(false);
        btn3.setIconTextGap(1);
        btn3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn3.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        btnPlusSub.setBackground(new java.awt.Color(21, 20, 22));
        btnPlusSub.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnPlusSub.setForeground(new java.awt.Color(255, 255, 255));
        btnPlusSub.setText("+/-");
        btnPlusSub.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnPlusSub.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlusSub.setFocusPainted(false);
        btnPlusSub.setIconTextGap(1);
        btnPlusSub.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnPlusSub.setPreferredSize(new java.awt.Dimension(70, 70));
        btnPlusSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusSubActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnPlusSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        btn0.setBackground(new java.awt.Color(21, 20, 22));
        btn0.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btn0.setForeground(new java.awt.Color(255, 255, 255));
        btn0.setText("0");
        btn0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btn0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn0.setFocusPainted(false);
        btn0.setIconTextGap(1);
        btn0.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn0.setPreferredSize(new java.awt.Dimension(70, 70));
        buttonsPanel.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        btnDot.setBackground(new java.awt.Color(21, 20, 22));
        btnDot.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDot.setForeground(new java.awt.Color(255, 255, 255));
        btnDot.setText(".");
        btnDot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnDot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDot.setFocusPainted(false);
        btnDot.setIconTextGap(1);
        btnDot.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnDot.setPreferredSize(new java.awt.Dimension(70, 70));
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnDot, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        btnEqual.setBackground(new java.awt.Color(41, 39, 44));
        btnEqual.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnEqual.setForeground(new java.awt.Color(255, 255, 255));
        btnEqual.setText("=");
        btnEqual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 39, 44)));
        btnEqual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEqual.setFocusPainted(false);
        btnEqual.setIconTextGap(1);
        btnEqual.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEqual.setPreferredSize(new java.awt.Dimension(70, 70));
        btnEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualActionPerformed(evt);
            }
        });
        buttonsPanel.add(btnEqual, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        app.add(buttonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 320, 390));

        titleBar.setBackground(new java.awt.Color(21, 20, 22));
        titleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titleBarMouseDragged(evt);
            }
        });
        titleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titleBarMousePressed(evt);
            }
        });
        titleBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Calculator");
        title.setPreferredSize(new java.awt.Dimension(84, 18));
        title.setRequestFocusEnabled(false);
        titleBar.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 2, -1, 25));

        btnMini.setBackground(new java.awt.Color(21, 20, 22));
        btnMini.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        btnMini.setForeground(new java.awt.Color(255, 255, 255));
        btnMini.setText("-");
        btnMini.setBorder(null);
        btnMini.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMini.setFocusPainted(false);
        btnMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniMouseExited(evt);
            }
        });
        btnMini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiniActionPerformed(evt);
            }
        });
        titleBar.add(btnMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 30, -1));

        btnClose.setBackground(new java.awt.Color(21, 20, 22));
        btnClose.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("×");
        btnClose.setBorder(null);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.setFocusPainted(false);
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCloseMouseExited(evt);
            }
        });
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        titleBar.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 30, -1));

        app.add(titleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(app, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(app, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed

    }//GEN-LAST:event_btnDelActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultActionPerformed
    }//GEN-LAST:event_btnMultActionPerformed

    private void btnSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubActionPerformed
    }//GEN-LAST:event_btnSubActionPerformed

    private void btnDivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivActionPerformed
        chooseOperation("÷");
    }//GEN-LAST:event_btnDivActionPerformed

    private void btnEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualActionPerformed
        this.compute();
        this.updateDisplay();
        if (this.currentOperand.equals("Error"))
            this.currentOperand = "";
    }//GEN-LAST:event_btnEqualActionPerformed

    private void btnPlusSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusSubActionPerformed
        if (!this.currentOperand.isEmpty()) {
            float tmp = -Float.parseFloat(this.currentOperand);
            this.currentOperand = (tmp - (int) tmp) != 0 ? Float.toString(tmp) : Integer.toString((int) tmp);
            this.updateDisplay();
        }
    }//GEN-LAST:event_btnPlusSubActionPerformed

    private void btnCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseEntered
        btnClose.setBackground(new Color(255, 75, 75));
        btnClose.setForeground(new Color(31, 30, 33));
    }//GEN-LAST:event_btnCloseMouseEntered

    private void btnCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseExited
        btnClose.setBackground(new Color(21,20,22));
        btnClose.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCloseMouseExited

    private void btnMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseEntered
        btnMini.setBackground(new Color(73, 69, 78));
    }//GEN-LAST:event_btnMiniMouseEntered

    private void btnMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseExited
        btnMini.setBackground(new Color(21,20,22));
    }//GEN-LAST:event_btnMiniMouseExited

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMiniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiniActionPerformed
        setState(Calculator.ICONIFIED);
    }//GEN-LAST:event_btnMiniActionPerformed

    private void titleBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleBarMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_titleBarMousePressed

    private void titleBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleBarMouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_titleBarMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel app;
    private static javax.swing.JButton btn0;
    private static javax.swing.JButton btn1;
    private static javax.swing.JButton btn2;
    private static javax.swing.JButton btn3;
    private static javax.swing.JButton btn4;
    private static javax.swing.JButton btn5;
    private static javax.swing.JButton btn6;
    private static javax.swing.JButton btn7;
    private static javax.swing.JButton btn8;
    private static javax.swing.JButton btn9;
    private static javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private static javax.swing.JButton btnDel;
    private static javax.swing.JButton btnDiv;
    private static javax.swing.JButton btnDot;
    private static javax.swing.JButton btnEqual;
    private javax.swing.JButton btnMini;
    private static javax.swing.JButton btnMult;
    private static javax.swing.JButton btnPlus;
    private static javax.swing.JButton btnPlusSub;
    private static javax.swing.JButton btnSub;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JTextField current;
    private javax.swing.JTextField previous;
    private javax.swing.JPanel resultsPanel;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titleBar;
    // End of variables declaration//GEN-END:variables

