package Calc;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class Calculator extends JFrame {
    
    
    // ------------------ Singleton ------------------

    private static Calculator instance = null;

    private Calculator() {
        initComponents();
        addEvents();
        updateDisplay();
    }

    public static Calculator getInstance() {
        if (instance == null){
            
      instance = new Calculator();
        }
        return instance;
    }
    // ------------------ Facade ------------------
    private final CalculatorFacade facade = new CalculatorFacade();
    private int x, y;

    private JPanel app, buttonsPanel, resultsPanel, titleBar;
    private JTextField current, previous;
    private JLabel title;
    private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private JButton btnPlus, btnSub, btnMult, btnDiv, btnEqual, btnClear, btnDel, btnDot, btnPlusSub;

  
    private JButton btnPower, btnRoot, btnLog, btnMod;

    private JButton createButton(String text, Color bgColor, Color fgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Century Gothic", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(bgColor));
        button.setBounds(0, 0, width, height);
        return button;
    }

    private void initComponents() {
        app = new JPanel(null);
        resultsPanel = new JPanel(null);
        buttonsPanel = new JPanel(null);
        titleBar = new JPanel(null);
        title = new JLabel("Calculator");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocation(new Point(500, 100));
        setUndecorated(true);
        setResizable(false);
        setSize(320, 600); //  زودنا الطول شوي لأننا بنضيف صف جديد

        app.setBackground(new Color(13, 12, 20));
        app.setLayout(null);

        resultsPanel.setBackground(new Color(34, 34, 34));
        resultsPanel.setBounds(0, 30, 320, 110);

        previous = new JTextField();
        previous.setEditable(false);
        previous.setBackground(new Color(21, 20, 22));
        previous.setFont(new Font("Century Gothic", Font.BOLD, 18));
        previous.setForeground(new Color(203, 198, 213));
        previous.setHorizontalAlignment(JTextField.RIGHT);
        previous.setBorder(null);
        previous.setBounds(0, 0, 320, 50);
        resultsPanel.add(previous);

        current = new JTextField();
        current.setEditable(false);
        current.setBackground(new Color(41, 39, 44));
        current.setFont(new Font("Century Gothic", Font.BOLD, 24));
        current.setForeground(Color.WHITE);
        current.setHorizontalAlignment(JTextField.RIGHT);
        current.setBorder(null);
        current.setBounds(0, 50, 320, 60);
        resultsPanel.add(current);

        app.add(resultsPanel);

        buttonsPanel.setBackground(new Color(21, 20, 22));
        buttonsPanel.setBounds(0, 140, 320, 460); //  زودنا الطول شوي

        // الأزرار الأساسية
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

    
        btnPower = createButton("^", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnRoot = createButton("√", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnLog = createButton("log", new Color(41, 39, 44), Color.WHITE, 70, 70);
        btnMod = createButton("%", new Color(41, 39, 44), Color.WHITE, 70, 70);

      
        btnPower.setBounds(20, 370, 70, 70);
        btnRoot.setBounds(90, 370, 70, 70);
        btnLog.setBounds(160, 370, 70, 70);
        btnMod.setBounds(230, 370, 70, 70);

      
        btnDel.setBounds(20, 20, 70, 70);
        btnClear.setBounds(90, 20, 70, 70);
        btnDiv.setBounds(160, 20, 70, 70);
        btnMult.setBounds(230, 20, 70, 70);

        btn7.setBounds(20, 90, 70, 70);
        btn8.setBounds(90, 90, 70, 70);
        btn9.setBounds(160, 90, 70, 70);
        btnSub.setBounds(230, 90, 70, 70);

        btn4.setBounds(20, 160, 70, 70);
        btn5.setBounds(90, 160, 70, 70);
        btn6.setBounds(160, 160, 70, 70);
        btnPlus.setBounds(230, 160, 70, 140);

        btn1.setBounds(20, 230, 70, 70);
        btn2.setBounds(90, 230, 70, 70);
        btn3.setBounds(160, 230, 70, 70);
        btnEqual.setBounds(230, 300, 70, 70);

        btnPlusSub.setBounds(20, 300, 70, 70);
        btn0.setBounds(90, 300, 70, 70);
        btnDot.setBounds(160, 300, 70, 70);

      
        buttonsPanel.add(btnPower);
        buttonsPanel.add(btnRoot);
        buttonsPanel.add(btnLog);
        buttonsPanel.add(btnMod);

    
        buttonsPanel.add(btnDel);
        buttonsPanel.add(btnClear);
        buttonsPanel.add(btnDiv);
        buttonsPanel.add(btnMult);
        buttonsPanel.add(btn7);
        buttonsPanel.add(btn8);
        buttonsPanel.add(btn9);
        buttonsPanel.add(btnSub);
        buttonsPanel.add(btn4);
        buttonsPanel.add(btn5);
        buttonsPanel.add(btn6);
        buttonsPanel.add(btnPlus);
        buttonsPanel.add(btn1);
        buttonsPanel.add(btn2);
        buttonsPanel.add(btn3);
        buttonsPanel.add(btnEqual);
        buttonsPanel.add(btnPlusSub);
        buttonsPanel.add(btn0);
        buttonsPanel.add(btnDot);

        app.add(buttonsPanel);

        titleBar.setBackground(new Color(21, 20, 22));
        titleBar.setBounds(0, 0, 320, 30);

        title.setFont(new Font("Century Gothic", Font.BOLD, 17));
        title.setForeground(Color.WHITE);
        title.setBounds(6, 2, 200, 25);
        titleBar.add(title);

        app.add(titleBar);
        add(app);
    }

    private void addEvents() {
        JButton[] numbers = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (int i = 0; i < numbers.length; i++) {
            int num = i;
            numbers[i].addActionListener(e -> {
                facade.appendNumber(Integer.toString(num));
                updateDisplay();
            });
        }

        btnPlus.addActionListener(e -> { facade.chooseOperation("+"); updateDisplay(); });
        btnSub.addActionListener(e -> { facade.chooseOperation("-"); updateDisplay(); });
        btnMult.addActionListener(e -> { facade.chooseOperation("×"); updateDisplay(); });
        btnDiv.addActionListener(e -> { facade.chooseOperation("÷"); updateDisplay(); });

      
        btnPower.addActionListener(e -> { facade.chooseOperation("^"); updateDisplay(); });
        btnRoot.addActionListener(e -> { facade.chooseOperation("root"); updateDisplay(); });
        btnLog.addActionListener(e -> { facade.chooseOperation("log"); updateDisplay(); });
        btnMod.addActionListener(e -> { facade.chooseOperation("%"); updateDisplay(); });

        btnDot.addActionListener(e -> { facade.appendNumber("."); updateDisplay(); });

  btnEqual.addActionListener(e -> {
    try {
        facade.compute();
    } catch (Exception ex) {
        facade.showError();   
    }

    updateDisplay();
});


        btnClear.addActionListener(e -> { facade.clear(); updateDisplay(); });
        btnDel.addActionListener(e -> { facade.deleteLast(); updateDisplay(); });
        btnPlusSub.addActionListener(e -> { facade.togglePlusMinus(); updateDisplay(); });

        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) { x = evt.getX(); y = evt.getY(); }
        });
        titleBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                setLocation(evt.getXOnScreen() - x, evt.getYOnScreen() - y);
            }
        });
    }

    private void updateDisplay() {
        current.setText(facade.getCurrentDisplay());
        previous.setText(facade.getPreviousDisplay());
    }
}
