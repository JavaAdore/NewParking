/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapapplet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

/**
 *
 * @author orcl
 */
public class Applet2 extends java.applet.Applet {

    /**
     * Initializes the applet Applet2
     */
    static Applet2 instance;
    JScrollPane imageScrollPane;
    DoorDetailsForm doorDetailsForm;
    HashMap< String, DoorButton> doors = new HashMap<>();
    HashMap< Integer, SlotButton> slots = new HashMap<>();
    HashMap< String, PointButton> paths = new HashMap<>();
    HashMap< String, MyObject> myHashMap = new HashMap<>();
    JLabel image = new JLabel();
    JLabel doorName = new JLabel();
    JLabel currentSlotId = new JLabel();
    DoorButton currentDoor;
    SlotButton currentSlot;
    PointButton currentPoint;
    MyPanel myPanel;
    JScrollPane jScrollPane1;
    int widthDiversion = 0;
    int heightDiversin = 0;

    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    initMyComponenets();
                    InitializeListeners();


                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void initMyComponenets() {
        instance = this;
        String path = getParameter("path");
        myPanel = new MyPanel();
        myPanel.setLayout(null);
        doorDetailsForm = new DoorDetailsForm(instance);
        jScrollPane1 = new JScrollPane(myPanel);
        this.add(jScrollPane1);
        jScrollPane1.updateUI();


    }

    private void InitializeListeners() {
        myPanel.addMouseListener(new ImageListener());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void handleThisDoor(DoorButton door) {
        if (doors.get(door.getDoorName().toUpperCase()) != null) {
            JOptionPane.showMessageDialog(null, "sorry this door name is already added");
            return;
        }

        doors.put(door.getDoorName().toUpperCase(), door);

        door.addMouseListener(new ElementMouseListener());
        door.addKeyListener(new ButtonKeyListener());
        door.addActionListener(new ButtonActionListener());
        doorName.setText(door.getDoorName());
        door.setSize(Constants.getDOOR_BUTTON_WIDTH(), Constants.getDOOR_BUTTON_HEIGHT());

        myPanel.add(door);
        currentDoor = door;

        SwingUtilities.updateComponentTreeUI(myPanel);


    }

    class ImageListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.isControlDown()) {
                doorDetailsForm.setCurrentPoint(e.getPoint());
                doorDetailsForm.setVisible(true);

            } else if (e.isAltDown()) {
                String result = JOptionPane.showInputDialog(null, "please enter slot id");
                if (result != null) {
                    if (isNumeric(result)) {
                        if (slots.get(Integer.parseInt(result)) != null) {
                            JOptionPane.showMessageDialog(null, "Sorry this slot id is already exist please choose another one");
                            myPanel.add(imageScrollPane);

                            return;
                        }
                        SlotButton slotButton = prepareSlotButton(result, e);
                        slotButton.setSlotId(Integer.parseInt(result));
                        slotButton.setIcon(new ImageIcon(getClass().getResource("slotIcon.png")));
                        slotButton.addActionListener(new ButtonActionListener());
                        currentSlotId.setText(result);
                        myPanel.add(slotButton);
                        SwingUtilities.updateComponentTreeUI(myPanel);
                        slots.put(Integer.parseInt(result), slotButton);
                        currentSlot = slotButton;

                    } else {
                        System.out.println("not number");

                    }
                }
            } else if (e.isMetaDown()) {
                new AppletHandler(8, doors, slots, myHashMap);
            } else {

                if (currentDoor == null || currentSlot == null) {

                    JOptionPane.showMessageDialog(null, "Please add  door and slot then you can add path between them");
                    return;
                }

                PointButton tempPointButton = paths.get(currentDoor.getDoorName() + "-" + currentSlot.getSlotId());
                if (tempPointButton != null) {
                    PointButton pointButton = preparePointButton(e, tempPointButton.getColor());
                    paths.put((currentDoor.getDoorName() + "-" + currentSlot.getSlotId()), pointButton);
                    System.out.println("current slot id " + currentSlot.getSlotId() + " current door is" + currentDoor.getDoorId());

                    myHashMap.get((currentDoor.getDoorName() + "-" + currentSlot.getSlotId())).AddToList(pointButton);
                    addComponent(myPanel, pointButton);
                } else {
                    Color tempColor = createRandomColor();
                    PointButton pointButton = preparePointButton(e, tempColor);
                    paths.put((currentDoor.getDoorName() + "-" + currentSlot.getSlotId()), pointButton);
                    myHashMap.put((currentDoor.getDoorName() + "-" + currentSlot.getSlotId()), new MyObject(currentSlot.getSlotId(), currentDoor.getDoorId()));
                    System.out.println("current slot id " + currentSlot.getSlotId() + " current door is" + currentDoor.getDoorId());
                    myHashMap.get((currentDoor.getDoorName() + "-" + currentSlot.getSlotId())).AddToList(pointButton);
                    addComponent(myPanel, pointButton);
                }
            }



        }

        public boolean isNumeric(String s) {
            return (s.matches("[+]?\\d+") && s.length() < 6);
        }

        private SlotButton prepareSlotButton(String result, MouseEvent e) {
            SlotButton slotButton = new SlotButton();
            slotButton.setText(result + "");
            slotButton.setSlotId(Integer.parseInt(result));
            slotButton.setBounds((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.getSLOT_BUTTON_WIDTH(), Constants.getSLOT_BUTTON_HEIGHT());
            slotButton.addMouseListener(new ElementMouseListener());
            slotButton.addMouseMotionListener(new ElementMouseListener());
            slotButton.addKeyListener(new ButtonKeyListener());
            return slotButton;
        }

        private void updatePointName(PointButton pointButton, MouseEvent e) {
            pointButton.setText(String.format("(%d,%d)", e.getX(), e.getY()));
        }

        private void addComponent(JPanel myPanel, PointButton pointButton) {
            myPanel.add(pointButton);
            myPanel.updateUI();
        }

        private PointButton preparePointButton(int x, int y, Color c) {
            PointButton pointButton = new PointButton(currentDoor.getDoorName() + "-" + Integer.parseInt(currentSlotId.getText()), c);
            pointButton.addMouseListener(new ElementMouseListener());
            pointButton.addMouseMotionListener(new ElementMouseListener());
            pointButton.addKeyListener(new ButtonKeyListener());
            pointButton.setBounds((int) x, y, Constants.getPOINT_BUTTON_WIDTH(), Constants.getPOINT_BUTTON_HEIGHT());
            pointButton.setX(x);
            pointButton.setY(y);
            pointButton.setForeground(c);
            return pointButton;

        }

        private PointButton preparePointButton(MouseEvent e, Color c) {
            PointButton pointButton = new PointButton(currentDoor.getDoorName() + "-" + Integer.parseInt(currentSlotId.getText()), c);
            pointButton.addActionListener(new ButtonActionListener());
            pointButton.addMouseListener(new ElementMouseListener());
            pointButton.addMouseMotionListener(new ElementMouseListener());
            pointButton.addKeyListener(new ButtonKeyListener());
            pointButton.setBounds((int) e.getPoint().getX(), (int) e.getPoint().getY(), Constants.getPOINT_BUTTON_WIDTH(), Constants.getPOINT_BUTTON_HEIGHT());
            updatePointName(pointButton, e);
            pointButton.setX((int) e.getPoint().getX());
            pointButton.setY((int) e.getPoint().getY());
            pointButton.setForeground(c);
            return pointButton;

        }

        private Color createRandomColor() {
            Random rand = new Random();
            return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
    }

    class ButtonKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                try {
                    myPanel.remove((Component) e.getSource());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(myPanel);
            }
        }
    }

    class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof DoorButton) {
                doorName.setText(((DoorButton) e.getSource()).getDoorName());
                currentDoor = (DoorButton) e.getSource();
            } else if (e.getSource() instanceof SlotButton) {
                currentSlotId.setText(((SlotButton) e.getSource()).getSlotId() + "");
                currentSlot = (SlotButton) e.getSource();

            }
        }
    }

    class ElementMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            widthDiversion = (int) (currentMouseLocation.getX() - e.getComponent().getX());

            heightDiversin = (int) (currentMouseLocation.getY() - e.getComponent().getY());

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isControlDown()) {
                ((JButton) e.getSource()).setLocation(e.getXOnScreen() - widthDiversion, e.getYOnScreen() - heightDiversin);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() instanceof DoorButton) {
                doorName.setText(((DoorButton) e.getSource()).getDoorName());
                currentDoor = (DoorButton) e.getSource();
            } else if (e.getSource() instanceof SlotButton) {
                currentSlotId.setText(((SlotButton) e.getSource()).getSlotId() + "");
                currentSlot = (SlotButton) e.getSource();

            } else if (e.getSource() instanceof PointButton) {
                currentPoint = (PointButton) e.getSource();

            }
        }
    }
}
