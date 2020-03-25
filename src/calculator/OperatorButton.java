/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

/**
 *
 * @author white_000
 */
public class OperatorButton extends CalculatorButton{
    JFormattedTextField entry;
    JLabel problem;
    public OperatorButton(String title, int xOffset, int yOffset, int wdth, int hght, JFormattedTextField tar, JLabel prob) {
        super(title, xOffset, yOffset, wdth, hght);
        entry = tar;
        problem = prob;
    }
    
}
