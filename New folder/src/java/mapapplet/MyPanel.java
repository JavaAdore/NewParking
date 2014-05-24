/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author orcl
 */
public class MyPanel extends JPanel {
    
    BufferedImage image;
    public MyPanel()
    {
        super();
        try {
            this.image = ImageIO.read(new File("D:\\a.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         Constants.initializeDimension(image.getWidth(),image.getHeight());
          
        this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
          g.drawImage(image, 0  , 0, this);

    }
}
