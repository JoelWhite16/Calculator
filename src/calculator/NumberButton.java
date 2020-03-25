/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

/**
 *
 * @author white_000
 */
public class NumberButton extends CalculatorButton {
    JFormattedTextField target;
    public void appendContent(){
        target.setText(target.getText() + content);
    }
    
    public NumberButton(String title, int xOffset, int yOffset, int wdth, int hght, JFormattedTextField tar) {
        super(title, xOffset, yOffset, wdth, hght);
        target = tar;
        button.addActionListener((ActionEvent e) -> {
            appendContent();
        });  
    }
    
}
