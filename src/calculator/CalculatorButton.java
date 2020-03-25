
package calculator;

import javax.swing.JButton;
/**
 *
 * @author Joel White
 */
public class CalculatorButton {
    String content;
    int height;
    int width;
    int x;
    int y;
    JButton button;
    
    public CalculatorButton(String title, int xOffset, int yOffset, int wdth, int hght){
        height = hght;
        width = wdth;
        x = xOffset;
        y = yOffset;
        content = title;
        button = new JButton(title);
        button.setBounds(x,y,width,height);
        
    }
}
