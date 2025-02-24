import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;

public class Calculator extends JFrame {
    private JTextArea area = new JTextArea(3, 20);
    private JButton[] digits = new JButton[10];
    private JButton[] operators = {
            new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/"),
            new JButton("("), new JButton(")"),
            new JButton("="), new JButton("C")
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }

    public Calculator() {
        setTitle("Java-Calc, PP Lab1");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < 10; i++) {
            digits[i] = new JButton(String.valueOf(i));
            buttonPanel.add(digits[i]);
            int finalI = i;
            digits[i].addActionListener(e -> area.append(String.valueOf(finalI)));
        }

        for (JButton operator : operators) {
            buttonPanel.add(operator);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Butonul C - curăță zona de text
        operators[7].addActionListener(e -> area.setText(""));
        // Butonul = - calculează expresia
        operators[6].addActionListener(e -> area.setText(evaluateExpression(area.getText())));

        // Adaugă operatorii și parantezele în zona de text la apăsare
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            operators[i].addActionListener(e -> area.append(operators[finalI].getText()));
        }

        setVisible(true);
    }

    private String evaluateExpression(String expression) {
        try {
            return String.valueOf(evaluatePostfix(convertToPostfix(expression)));
        } catch (Exception e) {
            return "Error";
        }
    }

    // Metoda tokenize: extrage numere (inclusiv zecimale), operatori și paranteze
    private String[] tokenize(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                if (!Character.isWhitespace(c)) {
                    tokens.add(String.valueOf(c));
                }
            }
        }
        if (number.length() > 0) {
            tokens.add(number.toString());
        }
        return tokens.toArray(new String[0]);
    }

    // Conversia expresiei infixate în notație postfixată
    private String convertToPostfix(String infix) {
        String[] tokens = tokenize(infix);
        StringBuilder output = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) { // număr întreg sau zecimal
                output.append(token).append(" ");
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
            } else { // operator
                while (!stack.isEmpty() && precedence(stack.peek().charAt(0)) >= precedence(token.charAt(0))) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString();
    }

    // Evaluarea expresiei postfixate folosind tipul double
    private double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix.split(" ")) {
            if (token.isEmpty()) continue;
            if (token.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/": stack.push(a / b); break;
                }
            }
        }

        return stack.pop();
    }

    // Determină prioritatea operatorilor
    private int precedence(char operator) {
        return (operator == '+' || operator == '-') ? 1 :
                (operator == '*' || operator == '/') ? 2 :
                        (operator == '(') ? 0 : -1;
    }
}
