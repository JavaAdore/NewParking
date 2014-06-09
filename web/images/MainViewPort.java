

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainViewPort {

    public static void main(String[] args) {
        JPanel viewportPanelTop = new JPanel();
        JPanel viewportPanelBottom = new JPanel();
        JPanel viewportPanel = new JPanel();
        viewportPanel.setBackground(Color.BLACK);
        viewportPanel.setPreferredSize(new Dimension(1000, 1000));
        JPanel viewportPanel1 = new JPanel();
        viewportPanel1.setBackground(Color.red);
        JPanel viewportPanel2 = new JPanel();
        viewportPanel2.setBackground(Color.blue);
        JPanel viewportPanel3 = new JPanel();
        viewportPanel3.setBackground(Color.cyan);
        JPanel viewportPanel4 = new JPanel();
        viewportPanel4.setBackground(Color.yellow);
        JPanel viewportPanel5 = new JPanel();
        viewportPanel5.setBackground(Color.LIGHT_GRAY);
        JPanel viewportPanel6 = new JPanel();
        viewportPanel6.setBackground(Color.magenta);
        viewportPanel.setLayout(new GridLayout(3, 2));
        viewportPanel.add(viewportPanel1);
        viewportPanel.add(viewportPanel2);
        viewportPanel.add(viewportPanel3);
        viewportPanel.add(viewportPanel4);
        viewportPanel.add(viewportPanel5);
        viewportPanel.add(viewportPanel6);

        viewportPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(800, 600));
        scrollPane.setBackground(Color.BLUE);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().add(viewportPanel);
        scrollPane.getViewport().setViewPosition(new Point(0, 0));
        scrollPane.getViewport().setBackground(Color.red);
        scrollPane.setBorder(new LineBorder(Color.black, 2));

        JFrame frame = new JFrame();
        frame.setTitle("Viewport JFrame");
        frame.setLocation(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(viewportPanelTop, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(viewportPanelBottom, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private MainViewPort() {
    }
}