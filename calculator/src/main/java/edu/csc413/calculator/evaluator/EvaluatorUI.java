package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EvaluatorUI extends JFrame implements ActionListener {

    private JTextField expressionTextField = new JTextField();
    private JPanel buttonPanel = new JPanel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+",
        "4", "5", "6", "-", 
        "1", "2", "3", "*", 
        "(", "0", ")", "/", 
        "C", "CE", "=", "^"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private JButton[] buttons = new JButton[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 24));
        this.expressionTextField.setHorizontalAlignment(JTextField.CENTER);

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        JButton tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new JButton(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 24));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {
        int i = -1;
        boolean checkClicked = false;

        while (i<20 && !checkClicked) {
            i++;
            if (actionEventObject.getSource().equals(buttons[i])) {
                checkClicked = true;
            }
        }

        //if not C, CE, or = button
        if (i != 16 && i != 17 && i != 18) {
            expressionTextField.setText(expressionTextField.getText() + buttonText[i]);
        }

        //if choose C button
        if (actionEventObject.getSource() == buttons[16]) {
            String currentText = expressionTextField.getText();
            if (!currentText.isEmpty()) {
                expressionTextField.setText(currentText.substring(0, currentText.length() - 1));
            }
        }

        //if choose CE button
        if (actionEventObject.getSource() == buttons[17]) {
            expressionTextField.setText("");
        }

        //if choose = button
        if (actionEventObject.getSource() == buttons[18]) {
            Evaluator evaluator = new Evaluator();
            String expression = expressionTextField.getText();
            if (!expression.isEmpty()) {
                try {
                    expressionTextField.setText(String.valueOf(evaluator.evaluateExpression(expression)));
                } catch (InvalidTokenException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
