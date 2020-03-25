
package calculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Calculator {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setIconImage(new ImageIcon("resources/calcIcon.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Main Input Area */
        DecimalFormat format = new DecimalFormat();
        JFormattedTextField entryArea = new JFormattedTextField(format);
        entryArea.setColumns(20);
        entryArea.setBounds(0,0,300,50);
        frame.add(entryArea);
        JLabel problemArea = new JLabel("");
        problemArea.setBounds(10,50,300,25);
        frame.add(problemArea);
        /* Calculator Buttons */
        NumberButton numButtons[] = new NumberButton[10];
        int buttonWidth = 50;
        int buttonHeight = 50;
        int buttonTopOffset = 150;
        int buttonLeftOffset = 10;
        
        for(int x = 1; x < numButtons.length;x++){
            int currentX = ((x-1)%3)*buttonWidth;
            numButtons[x] = new NumberButton(Integer.toString(x),currentX+buttonLeftOffset,buttonTopOffset,buttonWidth,buttonHeight,entryArea);
            frame.add(numButtons[x].button);
            if(x%3==0){
                buttonTopOffset = buttonTopOffset + buttonHeight;
            }
        }
        NumberButton zero = new NumberButton("0",buttonWidth+buttonLeftOffset,buttonTopOffset,buttonWidth,buttonHeight,entryArea);
        numButtons[0] = zero;
        frame.add(zero.button);
        /* Calculator Function Buttons */
        
        //ENTER
        CalculatorButton enter = new CalculatorButton("=",2*buttonWidth+buttonLeftOffset,buttonTopOffset,buttonWidth,buttonHeight);
        enter.button.addActionListener((ActionEvent e) -> {
            double answer = 0;
            String expression = problemArea.getText() + entryArea.getText();
            int numStart = 0;
            List<String> operators = new ArrayList<> ();
            List<Double> numbers = new ArrayList<>();
            for(int x = 0;x<expression.length();x++){
                switch(expression.charAt(x)){
                    case '+':
                        numbers.add(Double.parseDouble(expression.substring(numStart,x)));
                        operators.add("+");
                        numStart = x+1;
                        break;
                    case '-':
                        numbers.add(Double.parseDouble(expression.substring(numStart,x)));
                        operators.add("-");
                        numStart = x+1;
                        break;
                    case '*':
                        numbers.add(Double.parseDouble(expression.substring(numStart,x)));
                        operators.add("*");
                        numStart = x+1;
                        break;
                    case '/':
                        numbers.add(Double.parseDouble(expression.substring(numStart,x)));
                        operators.add("/");
                        numStart = x+1;
                        break;
                    default:
                }
            }
            numbers.add(Double.parseDouble(expression.substring(numStart,expression.length())));
            
            answer = numbers.get(0);
            
            for(int x = 1; x < numbers.size();x++){
                switch(operators.get(x-1).charAt(0)){
                    case '+':
                        answer = answer + numbers.get(x);
                        break;
                    case '-':
                        answer = answer - numbers.get(x);
                        break;
                    case '*':
                        answer = answer * numbers.get(x);
                        break;
                    case '/':
                        answer = answer / numbers.get(x);
                        break;
                    default:
                }
            }
            
            entryArea.setText(Double.toString(answer));
            problemArea.setText("");
        });
        frame.add(enter.button);
        
        //DECIMAL POINT
        CalculatorButton dot = new CalculatorButton(".",buttonLeftOffset,buttonTopOffset,buttonWidth,buttonHeight);
        dot.button.addActionListener((ActionEvent e) -> {
            String currentText = entryArea.getText();
            if(currentText.indexOf('.') > -1){
                //Already a decimal point do not add one.
            }else{
                entryArea.setText(currentText + ".");
            }
        });
        frame.add(dot.button);
        //CLEAR BUTTON
        
        CalculatorButton clear = new CalculatorButton("Clear",10,75,265,50);
        clear.button.setFont(new Font("Arial", Font.PLAIN, 20));
        clear.button.addActionListener((ActionEvent e) -> {
            entryArea.setText("");
            problemArea.setText("");
        });
        frame.add(clear.button);
        
        /* Calculator Operator Buttons */
        buttonWidth = 100;
        buttonHeight = 50;
        buttonTopOffset = 150;
        buttonLeftOffset = 170;
        
        OperatorButton operatorButtons[] = new OperatorButton[4];
        String[] operators = {"+","-","*","/"};
        for(int x = 0; x<operatorButtons.length;x++){
            OperatorButton thisButton = new OperatorButton(operators[x],buttonLeftOffset,buttonTopOffset,buttonWidth,buttonHeight,entryArea,problemArea);
            operatorButtons[x] = thisButton;
            frame.add(thisButton.button);
            thisButton.button.setFont(new Font("Arial", Font.PLAIN, 20));
            thisButton.button.addActionListener((ActionEvent e) -> {
                if(entryArea.getText().length() == 0){
                    //Nothing in entry area
                    if(problemArea.getText().length() > 0){
                        //Swap operators
                        String prob = problemArea.getText().substring(0,problemArea.getText().length()-1);
                        problemArea.setText(prob + thisButton.content);
                    }else{
                        //Do nothing because there aren't any numbers entered yet.
                    }
                }else{
                    //concatonate problem text with entry and operator
                    problemArea.setText(problemArea.getText() + entryArea.getText()+thisButton.content);
                    entryArea.setText("");
                }
            });
            buttonTopOffset = buttonTopOffset + buttonHeight;
        }
        
        /* Create Frame */
        frame.setSize(300,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
}
