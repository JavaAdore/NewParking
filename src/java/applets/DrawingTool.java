package applets;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class DrawingTool extends JApplet {

    private MainPanel mainPanel;
    private Component glassPane;

    @Override
    public void init() {
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DrawingTool.class.getName()).log(Level.SEVERE, null, ex);
        }

        initGlassPane();

        mainPanel = new MainPanel(this);

        add(mainPanel);

        setSize(1240, 604);

        //     glassPane.setVisible(true);
    }

    private void initGlassPane() {

        glassPane = new CustomeGlassPane();

        setGlassPane(glassPane);

        glassPane.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                requestFocusInWindow();
            }
        });

    }

    @Override
    public Component getGlassPane() {
        return super.getGlassPane();

    }

    public void showGlassPane() {

        glassPane.setVisible(true);

        NullEventGlassPane();

    }

    public void hideGlassPane() {

        glassPane.setVisible(false);
    }

    private void NullEventGlassPane() {

        glassPane.addMouseListener(new MouseAdapter() {
        });
        glassPane.addMouseMotionListener(new MouseMotionAdapter() {
        });
        glassPane.addKeyListener(new KeyAdapter() {
        });
    }

    private class CustomeGlassPane extends JComponent {

        @Override
        protected void paintComponent(Graphics g) {

            Rectangle clip = g.getClipBounds();
            Color alphaWhite = new Color(1.0f, 1.0f, 1.0f, 0.65f);
            g.setColor(alphaWhite);
            g.fillRect(clip.x, clip.y, clip.width, clip.height);

            g.drawImage(new ImageIcon(getClass().getResource("loading.gif")).getImage(), 385, 40, this);

        }
    }
}
