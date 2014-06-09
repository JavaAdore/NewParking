/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author orcl
 */
public class PointButton extends JButton {

    int order = 1;
    String identifer;

    public String getIdentifer() {
        return identifer;
    }

    public void setIdentifer(String identifer) {
        this.identifer = identifer;
    }
    Color color;
    int x;
    int y;

    public PointButton() {
        super();
    }

    public PointButton(String identifer, Color color) {
        super();
       this.identifer = identifer;
        this.color = color;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

   

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
