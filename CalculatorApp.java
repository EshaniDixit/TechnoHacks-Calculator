/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };

    private double num1, num2;
    private String currentInput = "";
    private char operator;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        panel = new JPanel();
        textField = new JTextField();
        buttons = new JButton[buttonLabels.length];

        // Set frame properties
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Set panel properties
        panel.setLayout(new GridLayout(4, 4)); 
        panel.setBackground(Color.GRAY);

        // Set text field properties
        textField.setFont(new Font("Arial", Font.PLAIN, 36));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(true);

        // Initialize buttons and add action listeners
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = createButton(buttonLabels[i]);
            final int index = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(buttonLabels[index]);
                }
            });
        }
        for (int i = 0; i < buttonLabels.length; i++) {
            panel.add(buttons[i]);
        }

        // Add components to frame
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        if (Character.isDigit(label.charAt(0)) ) {
            button.setBackground(Color.WHITE);
        } else {
            button.setBackground(Color.ORANGE);
        }
        return button;
    }

    private void handleButtonClick(String label) {
        switch (label) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (!currentInput.isEmpty()) {
                    num1 = Double.parseDouble(currentInput);
                    operator = label.charAt(0);
                    currentInput = "";
                }
                break;
            case "=":
                if (!currentInput.isEmpty()) {
                    num2 = Double.parseDouble(currentInput);
                    double result = performOperation(num1, num2, operator);
                    textField.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                }
                break;
            case "C":
                textField.setText("");
                currentInput = "";
                break;
            case ".":
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                }
                break;
            default:
                currentInput += label;
                textField.setText(currentInput);
        }
    }

    private double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(null, "Cannot divide by zero.");
                    return 0;
                }
                return num1 / num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorApp();
            }
        });
    }
}
