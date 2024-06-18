import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private JButton[] digitButtons;
    private JButton[] operatorButtons;
    private JButton clearButton;
    private JButton equalsButton;
    private JPanel panel;

    private String currentInput;
    private double currentNumber;
    private char currentOperator;

    public Calculator() {
        super("계산기");

        currentInput = "";
        currentNumber = 0;
        currentOperator = ' ';

       
        JMenuBar menuBar = new JMenuBar();
        
      
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("종료");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

      
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("File");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Calculator.this,
                        "계산기 프로그램입니다.",
                        "계산기 정보",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

       
        displayField = new JTextField(10);
        displayField.setEditable(false);

        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(String.valueOf(i));
            digitButtons[i].addActionListener(this);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (JButton button : operatorButtons) {
            button.addActionListener(this);
        }

        clearButton = new JButton("AC");
        clearButton.addActionListener(this);

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        
        for (int i = 7; i <= 9; i++) {
            panel.add(digitButtons[i]);
        }
        panel.add(operatorButtons[0]);

        for (int i = 4; i <= 6; i++) {
            panel.add(digitButtons[i]);
        }
        panel.add(operatorButtons[1]);

        for (int i = 1; i <= 3; i++) {
            panel.add(digitButtons[i]);
        }
        panel.add(operatorButtons[2]);

        panel.add(clearButton);
        panel.add(digitButtons[0]);
        panel.add(equalsButton);
        panel.add(operatorButtons[3]);

        add(displayField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String buttonText = button.getText();

            if (buttonText.matches("[0-9]")) { 
                currentInput += buttonText;
                displayField.setText(currentInput);
            } else if (buttonText.equals("+") || buttonText.equals("-") ||
                    buttonText.equals("*") || buttonText.equals("/")) { 
                currentOperator = buttonText.charAt(0);
                currentNumber = Double.parseDouble(currentInput);
                currentInput = "";
            } else if (buttonText.equals("=")) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;
                switch (currentOperator) {
                    case '+':
                        result = currentNumber + secondNumber;
                        break;
                    case '-':
                        result = currentNumber - secondNumber;
                        break;
                    case '*':
                        result = currentNumber * secondNumber;
                        break;
                    case '/':
                        if (secondNumber != 0) {
                            result = currentNumber / secondNumber;
                        } else {
                            JOptionPane.showMessageDialog(this, "0으로 나눌 수 없습니다.", "에러", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
                displayField.setText(String.valueOf(result));
                currentInput = "";
            } else if (buttonText.equals("AC")) {
                currentInput = "";
                currentNumber = 0;
                currentOperator = ' ';
                displayField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
