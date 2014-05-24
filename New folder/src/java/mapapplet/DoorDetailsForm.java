/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

/**
 *
 * @author orcl
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DoorDetailsForm
        extends JFrame {

    JTextField doorName = new JTextField();
    JTextField lon = new JTextField();
    JTextField lat = new JTextField();
    JButton createObject = new JButton();
    Applet2 mapTool;
    Point CurrentPoint;

    public DoorDetailsForm(Applet2 mapTool)
            throws HeadlessException {
        super();
        this.mapTool = mapTool;
        this.setLayout(new GridLayout(0, 1));
        this.add(new JLabel("Door Name"));
        this.add(doorName);
        this.add(new JLabel("Longitude"));
        this.add(lon);
        this.add(new JLabel("Latitude"));
        this.add(lat);

        JButton addDoor = new JButton("Add");
        addDoor.addActionListener(new AddDoorListener());
        this.add(addDoor);



        setSize(new Dimension(400, 400));

    }

    void setCurrentPoint(Point point) {
        CurrentPoint = point;
    }

    class AddDoorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            DoorButton door = new DoorButton(doorName.getText(), Double.parseDouble(lat.getText()), Double.parseDouble(lon.getText()));
            door.setLocation(0, 0);
            door.setForeground(Color.RED);
         
            door.setText(doorName.getText());
            door.setLocation((int)CurrentPoint.getX(),(int)CurrentPoint.getY());
            door.setIcon(new ImageIcon(getClass().getResource("doorIcon.png")));
            mapTool.handleThisDoor(door);
            setVisible(false);

        }
    }
}
